package com.zh.framework.service;

import com.zh.framework.entity.Knowledge;

import java.util.List;

public interface KnowledgeService {

    public List<Knowledge> queryKnowledgesAndSort(String orderBy);

    public Knowledge queryKnowledgeById(String id);

    public void updateUseCount(int count,String id);

    public void updateKnowledge(Knowledge k);

    public void updateKnowledgeStatus(String id,String status);

    public void deleteKnowledge(String id);

    public void addKnowledge(Knowledge k);

}
