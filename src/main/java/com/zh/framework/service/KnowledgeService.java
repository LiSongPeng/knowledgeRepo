package com.zh.framework.service;

import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;

import java.util.List;

public interface KnowledgeService {
    public void updateKnowledge(Knowledge k);

    public void updateKnowledgeStatus(String id,String status);

    public void deleteKnowledge(String id);

    public void addKnowledge(Knowledge k);

    public PageBean queryAllKnowledge(PageBean pageBean);


}
