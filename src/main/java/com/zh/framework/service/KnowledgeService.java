package com.zh.framework.service;

import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.KnowledgeIndex;
import com.zh.framework.mapper.KnowledgeMapper;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.suggest.document.SuggestIndexSearcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by lihuibo on 17-8-28 上午9:58
 */
@Service("knowledgeService")
public class KnowledgeService {
    private KnowledgeMapper knowledgeMapper;
    private IndexWriter indexWriter;
    private IndexSearcher indexSearcher;

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
        return null;
    }

    /**
     * 查看知识详情
     *
     * @param id 要查看的知识id
     * @return 知识实体
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Knowledge viewKnowledgeDetail(String id) {
        return null;
    }

    /**
     * 根据关键字检索索引
     *
     * @param keyWord 关键字
     * @return 检索结果
     */
    public List<KnowledgeIndex> searchIndex(String keyWord) {
        return null;
    }

    /**
     * 更具关键字智能提示
     *
     * @param keyWord 关键字
     * @return 智能提示结果
     */
    public List<String> smartHint(String keyWord) {
        return null;
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
