package com.zh.framework.service;

import com.zh.framework.entity.Knowledge;
import com.zh.framework.thread.Handler;
import com.zh.framework.thread.Message;
import com.zh.framework.util.Constant;
import com.zh.framework.util.TypeTester;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * created by lihuibo on 17-9-1 上午9:28
 */
public class KnowledgeIndexHandler extends Handler {
    public static final int REMOVE = 1;
    public static final int BUILD = 2;
    public static final int UPDATE = 3;
    public static final int CLOSE = 4;
    public static final int BUILD_ALL = 5;
    private Directory directory;
    private IndexWriter indexWriter;

    public KnowledgeIndexHandler() {
        super();
        Path path = Paths.get(".", Constant.INDEX_DIRECTORY);
        try {
            directory = FSDirectory.open(path);
            IndexWriterConfig config = new IndexWriterConfig(new SmartChineseAnalyzer());
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(directory, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleMessage(Message message) {
        try {
            switch (message.what) {
                case REMOVE:
                    removeIndex((String) message.data);
                    break;
                case BUILD:
                    buildAIndex((Knowledge) message.data);
                    break;
                case UPDATE:
                    updateIndex((Knowledge) message.data);
                    break;
                case CLOSE:
                    close();
                case BUILD_ALL:
                    buildAllIndex((List<Knowledge>) message.data);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeIndex(String id) throws Exception {
        if (TypeTester.isEmpty(id))
            return;
        QueryParser queryParser = new QueryParser(Constant.INDEX_ID, indexWriter.getAnalyzer());
        Query query = queryParser.parse(id);
/*        TopDocs docs = indexSearcher.search(query, 1);
        if (docs.totalHits == 0)
            return;
        Document hitDoc = indexSearcher.doc(docs.scoreDocs[0].doc);*/
        indexWriter.deleteDocuments(query);
        indexWriter.commit();
    }

    public void buildAIndex(Knowledge k) throws IOException {
        Document doc = new Document();
        doc.add(new Field(Constant.INDEX_ID, k.getId(), TextField.TYPE_STORED));
        doc.add(new Field(Constant.K_TITLE, k.getkTitle(), TextField.TYPE_STORED));
        doc.add(new Field(Constant.K_ANSWER, k.getkAnswer(), TextField.TYPE_STORED));
        doc.add(new StoredField(Constant.K_USE_COUNT, k.getkUseCount()));
        doc.add(new NumericDocValuesField(Constant.K_USE_COUNT_SORT, k.getkUseCount()));
        indexWriter.addDocument(doc);
        indexWriter.commit();
    }

    public void updateIndex(Knowledge k) throws Exception {
        Document newDoc = new Document();
        newDoc.add(new Field(Constant.INDEX_ID, k.getId(), TextField.TYPE_STORED));
        newDoc.add(new Field(Constant.K_TITLE, k.getkTitle(), TextField.TYPE_STORED));
        newDoc.add(new Field(Constant.K_ANSWER, k.getkAnswer(), TextField.TYPE_STORED));
        newDoc.add(new StoredField(Constant.K_USE_COUNT, k.getkUseCount()));
        newDoc.add(new NumericDocValuesField(Constant.K_USE_COUNT_SORT, k.getkUseCount()));
        indexWriter.updateDocument(new Term(Constant.INDEX_ID, k.getId()), newDoc);
        indexWriter.commit();
    }

    public void buildAllIndex(List<Knowledge> list) throws IOException {
        Document doc;
        for (int i = 0; i < list.size(); i++) {
            doc = new Document();
            doc.add(new Field(Constant.INDEX_ID, list.get(i).getId(), TextField.TYPE_STORED));
            doc.add(new Field(Constant.K_TITLE, list.get(i).getkTitle(), TextField.TYPE_STORED));
            doc.add(new Field(Constant.K_ANSWER, list.get(i).getkAnswer(), TextField.TYPE_STORED));
            doc.add(new StoredField(Constant.K_USE_COUNT, list.get(i).getkUseCount()));
            doc.add(new NumericDocValuesField(Constant.K_USE_COUNT_SORT, list.get(i).getkUseCount()));
            indexWriter.addDocument(doc);
        }
        indexWriter.commit();
    }

    public void close() {
        try {
            indexWriter.close();
            directory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();//发中断关闭当前Looper线程
    }

}
