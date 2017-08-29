package com.zh.framework.mapper;

import com.zh.framework.entity.Knowledge;

import java.util.List;

/**
 * created by lihuibo on 17-8-28 上午9:59
 */
public interface KnowledgeMapper {
    public List<Knowledge> queryKnowledgesAndSort(String orderBy);

    public Knowledge queryKnowledgeById(String id);

    public void updateUseCount(int count, String id);

    public void updateKnowledge(Knowledge k);

    public void updateKnowledgeStatus(String id, String status);

    public void deleteKnowledge(String id);

    public void addKnowledge(Knowledge k);
}
