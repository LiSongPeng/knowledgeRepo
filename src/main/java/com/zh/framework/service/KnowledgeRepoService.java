package com.zh.framework.service;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.KnowledgeIndex;
import com.zh.framework.mapper.KnowledgeMapper;
import com.zh.framework.thread.Looper;
import com.zh.framework.thread.Message;
import com.zh.framework.util.Constant;
import com.zh.framework.util.TypeTester;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * created by lihuibo on 17-8-28 上午9:58
 */
@Service
@Scope("singleton")
public class KnowledgeRepoService {
    private KnowledgeMapper knowledgeMapper;
    private KnowledgeIndexHandler handler;
    Directory directory;
    DirectoryReader reader;

    private class LooperThread extends Thread {//Looper线程,接收KnowledgeService请求,并作出响应

        @Override
        public void run() {
            Looper.prepare();
            handler = new KnowledgeIndexHandler();
            Looper.loop();
        }
    }

    public KnowledgeRepoService() {
        Path path = Paths.get(".", Constant.INDEX_DIRECTORY);
        try {
            directory = FSDirectory.open(path);
            reader = DirectoryReader.open(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new LooperThread().start();
    }

    @Resource(name = "knowledgeMapper")
    public void setKnowledgeMapper(KnowledgeMapper knowledgeMapper) {
        this.knowledgeMapper = knowledgeMapper;
    }

    /**
     * 列表显示
     *
     * @param orderBy  根据哪个字段排序
     * @param page     要显示的页码
     * @param pageSize 要显示的每页面数据数
     * @return 结果集
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public PageInfo<Knowledge> listDisplay(String orderBy, int page, int pageSize, int order) {
/*        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(page, pageSize);*/
        List<Knowledge> list = knowledgeMapper.queryKnowledgesAndSort(orderBy, order);
        PageInfo<Knowledge> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 查看知识详情
     *
     * @param id 要查看的知识id
     * @return 知识实体
     */
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Knowledge viewKnowledgeDetail(String id) throws Exception {
        Knowledge k = knowledgeMapper.queryKnowledgeById(id);
        knowledgeMapper.updateUseCount(k.getkUseCount() + 1, id);
        knowledgeMapper.updateLastUseTime(new Date(), id);
        k.setkUseCount(k.getkUseCount() + 1);
        updateIndex(k);
        return k;
    }

    /**
     * 根据关键字检索索引
     *
     * @param keyWord 关键字
     * @param orderBy
     * @return 检索结果
     */
    public PageInfo<KnowledgeIndex> searchIndex(String keyWord, int page, int pageSize, int orderBy, int order) throws Exception {
        if (TypeTester.isEmpty(keyWord) || TypeTester.isNegative(page) || TypeTester.isNegative(pageSize))
            return null;
        Analyzer analyzer = new SmartChineseAnalyzer();
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{Constant.K_TITLE, Constant.K_ANSWER}, analyzer);
        boolean reverse = order == 1 ? true : false;
        Sort sort = new Sort(new SortField(null, SortField.Type.SCORE, reverse));//默认按相关度排序
        if (orderBy == Constant.ORDER_BY_K_USE_COUNT)
            sort = new Sort(new SortField(Constant.K_USE_COUNT_SORT, SortField.Type.INT, !reverse));
        if (orderBy == Constant.ORDER_BY_K_USE_COUNT_RELEVANCE)
            sort = new Sort(new SortField[]{new SortField(null, SortField.Type.SCORE, reverse), new SortField(Constant.K_USE_COUNT_SORT, SortField.Type.INT, !reverse)});
        Query query = parser.parse(keyWord);
        DirectoryReader newReader = DirectoryReader.openIfChanged(reader);
        if (newReader != null)
            reader = newReader;
        if (reader.numDocs() == 0)
            return null;
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        ScoreDoc[] scoreDocs = indexSearcher.search(query, page * pageSize, sort, true, false).scoreDocs;
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<B style='color:red'>", "</B>");
        QueryScorer queryScorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
        Highlighter highlighter = new Highlighter(formatter, queryScorer);
        highlighter.setTextFragmenter(fragmenter);
        List<KnowledgeIndex> indexList = new ArrayList<>();
        KnowledgeIndex index;
        Document doc;
        String id, kTitle, kAnswer, kUseCount, tmp;
        TokenStream kTitleStream;
        TokenStream kAnswerStream;
        int docsSize = scoreDocs.length;
        for (int i = (page - 1) * pageSize; i < docsSize && i < page * pageSize; i++) {
            index = new KnowledgeIndex();
            index.setScore(scoreDocs[i].score);
            doc = indexSearcher.doc(scoreDocs[i].doc);
            id = doc.get(Constant.INDEX_ID);
            index.setId(id);
            kTitle = doc.get(Constant.K_TITLE);
            kAnswer = doc.get(Constant.K_ANSWER);
            kUseCount = doc.get(Constant.K_USE_COUNT);
            index.setkUseCount(kUseCount);
            kTitleStream = analyzer.tokenStream(Constant.K_TITLE, kTitle);
            tmp = highlighter.getBestFragment(kTitleStream, kTitle);
            index.setkTitle(tmp == null ? kTitle : tmp);
            kAnswerStream = analyzer.tokenStream(Constant.K_ANSWER, kAnswer);
            tmp = highlighter.getBestFragment(kAnswerStream, kAnswer);
            index.setkAnswer(tmp == null ? kAnswer : tmp);
            indexList.add(index);
        }
        PageInfo<KnowledgeIndex> pageInfo = new PageInfo<>(indexList);
        pageInfo.setHasPreviousPage(page > 1);
        pageInfo.setHasNextPage(page * pageSize < docsSize);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(page);
        int pages = docsSize / pageSize;
        pageInfo.setPages(docsSize % pageSize == 0 ? pages : pages + 1);
        pageInfo.setSize(indexList.size());
        pageInfo.setIsFirstPage(page == 1);
        pageInfo.setPrePage(page - 1);
        pageInfo.setNextPage(page + 1);
        pageInfo.setTotal(docsSize);
        pageInfo.setIsFirstPage(page == 1);
        pageInfo.setIsLastPage(page == pageInfo.getPages());
        return pageInfo;
    }

    /**
     * 根据关键字检索索引
     *
     * @param keyWord 关键字
     * @return 结果集
     */
    public List<KnowledgeIndex> searchIndexNoPage(String keyWord) throws Exception {
        if (TypeTester.isEmpty(keyWord))
            return null;
        Analyzer analyzer = new SmartChineseAnalyzer();
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{Constant.K_TITLE, Constant.K_ANSWER}, analyzer);
        Query query = parser.parse(keyWord);
        Sort sort = new Sort(new SortField[]{SortField.FIELD_SCORE, new SortField(Constant.K_USE_COUNT_SORT, SortField.Type.INT, true)});
        DirectoryReader newReader = DirectoryReader.openIfChanged(reader);
        if (newReader != null)
            reader = newReader;
        if (reader.numDocs() == 0)
            return null;
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        ScoreDoc[] scoreDocs = indexSearcher.search(query, 100, sort, true, false).scoreDocs;
        List<KnowledgeIndex> indexList = new ArrayList<>();
        KnowledgeIndex index;
        Document doc;
        String id, kTitle, kAnswer, kUseCount;
        for (int i = 0; i < scoreDocs.length; i++) {
            index = new KnowledgeIndex();
            index.setScore(scoreDocs[i].score);
            doc = indexSearcher.doc(scoreDocs[i].doc);
            id = doc.get(Constant.INDEX_ID);
            index.setId(id);
            kTitle = doc.get(Constant.K_TITLE);
            kAnswer = doc.get(Constant.K_ANSWER);
            kUseCount = doc.get(Constant.K_USE_COUNT);
            index.setkUseCount(kUseCount);
            index.setkTitle(kTitle);
            index.setkAnswer(kAnswer);
            indexList.add(index);
        }
        return indexList;
    }

    /**
     * 根据用户输入关键字进行检索提示
     *
     * @param keyWord 关键字
     * @return 提示部分内容
     */
    public List<String> inputHint(String keyWord) throws Exception {
        if (TypeTester.isEmpty(keyWord))
            return null;
//        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{K_TITLE, K_ANSWER}, indexWriter.getAnalyzer());
        if (reader.numDocs() == 0)
            return null;
        QueryParser parser = new QueryParser(Constant.K_TITLE, new SmartChineseAnalyzer());
        Sort sort = new Sort(SortField.FIELD_SCORE);
        Query query = parser.parse(keyWord);
        DirectoryReader newReader = DirectoryReader.openIfChanged(reader);
        if (newReader != null)
            reader = newReader;
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        ScoreDoc[] scoreDocs = indexSearcher.search(query, 6, sort).scoreDocs;
        List<String> hintList = new ArrayList<>();
        Document doc;
        String kTitle;
        for (int i = 0; i < scoreDocs.length; i++) {
            doc = indexSearcher.doc(scoreDocs[i].doc);
            kTitle = doc.get(Constant.K_TITLE);
            hintList.add(kTitle);
        }
        return hintList;
    }

    /**
     * 为指定知识构建索引
     *
     * @param k 知识
     */
    public void buildAIndex(Knowledge k) throws IOException {
        Message message = new Message();
        message.what = KnowledgeIndexHandler.BUILD;
        message.data = k;
        handler.sendMessage(message);
    }

    public void buildAllIndex() throws IOException {
        List<Knowledge> list = knowledgeMapper.queryAllKnowledge();
        if (list == null || list.size() == 0)
            return;
        Message message = new Message();
        message.what = KnowledgeIndexHandler.BUILD_ALL;
        message.data = list;
        handler.sendMessage(message);
    }

    /**
     * 更新指定索引
     *
     * @param k 知识k
     */
    public void updateIndex(Knowledge k) throws Exception {
        if (k == null)
            return;
        Message message = new Message();
        message.what = KnowledgeIndexHandler.UPDATE;
        message.data = k;
        handler.sendMessage(message);
    }

    /**
     * 删除指定索引
     *
     * @param id 索引知识id
     * @Return 删除与否
     */
    public void removeIndex(String id) throws Exception {
        if (TypeTester.isEmpty(id))
            return;
        Message message = new Message();
        message.what = KnowledgeIndexHandler.REMOVE;
        message.data = id;
        handler.sendMessage(message);
    }

    /**
     * 知识列表
     *
     * @return
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Knowledge> knowledgeList() {
        return null;
    }

    /**
     * 修改Knowledge k
     *
     * @param k Knowledge
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean editKnowledge(Knowledge k) {
        return false;
    }

    /**
     * 删除知识,将知识状态改为删除待审核
     *
     * @param id 知识id
     * @return 是否删除
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean deleteKnowledge(String id) {
        return false;
    }

    /**
     * 知识审批
     *
     * @param id 知识id
     * @return 审批是否成功
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean approveKnowledge(String id) {
        return false;
    }

    @PreDestroy
    public void close() {
        Message message = new Message();
        message.what = KnowledgeIndexHandler.CLOSE;
        handler.sendMessage(message);
        try {
            directory.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
