package com.zh.framework.service;

import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;

import java.util.Date;
import java.util.List;

public interface KnowledgeService {
   // public void updateKnowledge(Knowledge k);
    public void updateKnowledge(String id,String kTitle,String createUserId,String kAnswer);
    public Knowledge queryKnowledgeById(String id);

    public void updateKnowledgeStatus(String id,String status);

    public void deleteKnowledge(String id);

    public void addKnowledge(Knowledge k);

    public PageBean queryAllKnowledge(PageBean pageBean);

    public void updateAppr(String id,String kApprUserId,String kApprMemo,Date kApprTime);
}
