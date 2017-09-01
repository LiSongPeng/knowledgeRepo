package com.zh.framework.service;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.KnowledgeIndex;

import java.io.IOException;
import java.util.List;

/**
 * created by lihuibo on 17-9-1 下午1:34
 */
public interface KnowledgeRepoService {
    /**
     * 分页获取知识列表
     *
     * @param orderBy  排序字段
     * @param page     页码
     * @param pageSize 每页数据数目
     * @param order    升序或降序
     * @return 查询结果
     */
    public PageInfo<Knowledge> listDisplay(String orderBy, int page, int pageSize, int order);

    /**
     * 查询特定id的知识详细,并更新使用次数和索引
     *
     * @param id 知识id
     * @return 特定的知识
     * @throws Exception
     */
    public Knowledge viewKnowledgeDetail(String id) throws Exception;

    /**
     * 按关键字分页查询知识索引
     *
     * @param keyWord  关键字
     * @param page     页码
     * @param pageSize 页数目
     * @param orderBy  排序字段
     * @param order    升序或降序
     * @return 结果
     * @throws Exception
     */
    public PageInfo<KnowledgeIndex> searchIndex(String keyWord, int page, int pageSize, int orderBy, int order) throws Exception;

    /**
     * 按关键字查询知识索引
     *
     * @param keyWord 关键字
     * @return 结果
     * @throws Exception
     */
    public List<KnowledgeIndex> searchIndexNoPage(String keyWord) throws Exception;

    /**
     * 输入提示
     *
     * @param keyWord 关键字
     * @return 提示信息
     * @throws Exception
     */
    public List<String> inputHint(String keyWord) throws Exception;

    /**
     * 为特定知识新建索引
     *
     * @param k 特定知识
     * @throws IOException
     */
    public void buildAIndex(Knowledge k) throws IOException;

    /**
     * 为所有知识新建索引
     *
     * @throws IOException
     */
    public void buildAllIndex() throws IOException;

    /**
     * 更新指定索引
     *
     * @param k 知识k
     */
    public void updateIndex(Knowledge k) throws Exception;

    /**
     * 删除指定索引
     *
     * @param id 索引知识id
     */
    public void removeIndex(String id) throws Exception;

    /**
     * 知识列表
     *
     * @return
     */
    public List<Knowledge> knowledgeList();

    /**
     * 修改Knowledge k
     *
     * @param k Knowledge
     */
    public boolean editKnowledge(Knowledge k);

    /**
     * 删除知识,将知识状态改为删除待审核
     *
     * @param id 知识id
     * @return 是否删除
     */
    public boolean deleteKnowledge(String id);

    /**
     * 知识审批
     *
     * @param id 知识id
     * @return 审批是否成功
     */
    public boolean approveKnowledge(String id);
}
