package com.zh.framework.service;

import com.sun.deploy.association.utility.AppAssociationReader;
import com.zh.framework.entity.ApprovalRecord;
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

    public PageBean querySomeKnowledge(PageBean pageBean);

    public PageBean queryKnowledgeOrder(PageBean pageBean);

    public PageBean queryKnowledgeOrder2(PageBean pageBean);

    public List<ApprovalRecord> queryAppar(String kid);

    public void addAppar(ApprovalRecord approvalRecord);

    public void updateAppr(String id,String kApprUserId,String kApprMemo,Date kApprTime);

    public PageBean search(String searchKey,String keyValue,PageBean pageBean);

 public List<Knowledge> queryByKtitle(String kTitle);
}
