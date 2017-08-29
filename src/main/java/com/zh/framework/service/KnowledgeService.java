package com.zh.framework.service;

import com.github.pagehelper.PageHelper;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.KnowledgeIndex;
import com.zh.framework.mapper.KnowledgeMapper;
import com.zh.framework.util.TypeTester;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * created by lihuibo on 17-8-28 上午9:58
 */
@Service("knowledgeService")
public class KnowledgeService {
    private KnowledgeMapper knowledgeMapper;
    private IndexWriter indexWriter;
    private IndexSearcher indexSearcher;
    private static final String INDEX_ID = "id";
    private static final String K_TITLE = "kTitle";
    private static final String K_ANSWER = "kAnswer";
    private static final String K_USE_COUNT = "kUseCount";
    private static final String K_USE_COUNT_SORT = "kUseCountSort";

    @Resource(name = "indexWriter")
    public void setIndexWriter(IndexWriter indexWriter) {
        this.indexWriter = indexWriter;
    }

    @Resource(name = "indexSearcher")
    public void setIndexSearcher(IndexSearcher indexSearcher) {
        this.indexSearcher = indexSearcher;
    }

    @Resource(name = "knowledgeMapper")
    public void setKnowledgeMapper(KnowledgeMapper knowledgeMapper) {
        this.knowledgeMapper = knowledgeMapper;
    }

    /**
     * 列表显示
     *
     * @param orderBy  根据那个字段排序
     * @param page     要显示的页码
     * @param pageSize 要显示的每页面数据数
     * @return 结果集
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Knowledge> listDisplay(String orderBy, int page, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(page, pageSize);
        List<Knowledge> list = knowledgeMapper.queryKnowledgesAndSort(orderBy);
        return list;
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
        updateIndex(k);
        return k;
    }

    /**
     * 根据关键字检索索引
     *
     * @param keyWord 关键字
     * @return 检索结果
     */
    public List<KnowledgeIndex> searchIndex(String keyWord, int page, int pageSize) throws Exception {
        if (TypeTester.isEmpty(keyWord) || TypeTester.isNegative(page) || TypeTester.isNegative(pageSize))
            return null;
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{K_TITLE, K_ANSWER}, indexWriter.getAnalyzer());
        Sort sort = new Sort(new SortField[]{SortField.FIELD_SCORE, new SortField(K_USE_COUNT_SORT, SortField.Type.INT, true)});
        Query query = parser.parse(keyWord);
        ScoreDoc[] scoreDocs = indexSearcher.search(query, page * pageSize, sort, true, false).scoreDocs;
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span class='highlight'>", "</span>");
        QueryScorer queryScorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
        Highlighter highlighter = new Highlighter(formatter, queryScorer);
        highlighter.setTextFragmenter(fragmenter);
        List<KnowledgeIndex> indexList = new ArrayList<>();
        KnowledgeIndex index;
        Document doc;
        String id, kTitle, kAnswer, kUseCount;
        TokenStream kTitleStream;
        TokenStream kAnswerStream;
        for (int i = (page - 1) * pageSize; i < page * pageSize; i++) {
            index = new KnowledgeIndex();
            index.setScore(scoreDocs[i].score);
            doc = indexSearcher.doc(scoreDocs[i].doc);
            id = doc.get(INDEX_ID);
            index.setId(id);
            kTitle = doc.get(K_TITLE);
            kAnswer = doc.get(K_ANSWER);
            kUseCount = doc.get(K_USE_COUNT);
            index.setkUseCount(kUseCount);
            kTitleStream = indexWriter.getAnalyzer().tokenStream(K_TITLE, kTitle);
            index.setkTitle(highlighter.getBestFragment(kTitleStream, kTitle));
            kAnswerStream = indexWriter.getAnalyzer().tokenStream(K_ANSWER, kAnswer);
            index.setkAnswer(highlighter.getBestFragment(kAnswerStream, kAnswer));
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
        QueryParser parser = new QueryParser(K_TITLE, indexWriter.getAnalyzer());
        Sort sort = new Sort(SortField.FIELD_SCORE);
        Query query = parser.parse(keyWord);
        ScoreDoc[] scoreDocs = indexSearcher.search(query, 6, sort).scoreDocs;
        List<String> hintList = new ArrayList<>();
        Document doc;
        String kTitle;
        for (int i = 0; i < scoreDocs.length; i++) {
            doc = indexSearcher.doc(scoreDocs[i].doc);
            kTitle = doc.get(K_TITLE);
            hintList.add(kTitle);
        }
        return hintList;
    }

    /**
     * 为指定知识构建索引
     *
     * @param k 知识
     */
    public void buildIndex(Knowledge k) throws IOException {
        Document doc = new Document();
        doc.add(new Field(INDEX_ID, k.getId(), TextField.TYPE_STORED));
        doc.add(new Field(K_TITLE, k.getkTitle(), TextField.TYPE_STORED));
        doc.add(new Field(K_ANSWER, k.getkAnswer(), TextField.TYPE_STORED));
        doc.add(new Field(K_ANSWER, k.getkAnswer(), TextField.TYPE_STORED));
        doc.add(new StoredField(K_USE_COUNT, k.getkUseCount()));
        doc.add(new NumericDocValuesField(K_USE_COUNT_SORT, k.getkUseCount()));
        indexWriter.addDocument(doc);
        indexWriter.commit();
    }

    /**
     * 更新指定索引
     *
     * @param k 知识k
     */
    public void updateIndex(Knowledge k) throws Exception {
        QueryParser queryParser = new QueryParser(INDEX_ID, indexWriter.getAnalyzer());
        Query query = queryParser.parse(k.getId());
        TopDocs docs = indexSearcher.search(query, 1);
        if (docs.totalHits == 0)
            return;
        Document hitDoc = indexSearcher.doc(docs.scoreDocs[0].doc);
        String kTitle = hitDoc.get(K_TITLE);
        String kAnswer = hitDoc.get(K_ANSWER);
        String kUseCount = hitDoc.get(K_USE_COUNT);
        if (!kTitle.equals(k.getkTitle())) {
            hitDoc.removeField(K_TITLE);
            hitDoc.add(new Field(K_TITLE, k.getkTitle(), TextField.TYPE_STORED));
        }
        if (!kAnswer.equals(k.getkAnswer())) {
            hitDoc.removeField(K_ANSWER);
            hitDoc.add(new Field(K_ANSWER, k.getkAnswer(), TextField.TYPE_STORED));
        }
        if (Integer.valueOf(kUseCount) != (k.getkUseCount())) {
            hitDoc.removeField(K_USE_COUNT);
            hitDoc.removeField(K_USE_COUNT_SORT);
            hitDoc.add(new StoredField(K_USE_COUNT, k.getkUseCount()));
            hitDoc.add(new NumericDocValuesField(K_USE_COUNT_SORT, k.getkUseCount()));
        }
    }

    /**
     * 删除指定索引
     *
     * @param id 索引知识id
     * @Return 删除与否
     */
    public boolean removeIndex(String id) throws Exception {
        if (TypeTester.isEmpty(id))
            return false;
        QueryParser queryParser = new QueryParser(INDEX_ID, indexWriter.getAnalyzer());
        Query query = queryParser.parse(id);
/*        TopDocs docs = indexSearcher.search(query, 1);
        if (docs.totalHits == 0)
            return;
        Document hitDoc = indexSearcher.doc(docs.scoreDocs[0].doc);*/
        long result = indexWriter.deleteDocuments(query);
        indexWriter.commit();
        return result > 0;
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
}