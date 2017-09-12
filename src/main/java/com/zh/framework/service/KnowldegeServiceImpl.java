package com.zh.framework.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;
import com.zh.framework.mapper.KnowledgeMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("knowledgeService")
//public class KnowldegeServiceImpl extends BaseServiceImpl<Knowledge> implements KnowledgeService{
public class KnowldegeServiceImpl implements KnowledgeService {
    @Autowired
    KnowledgeMapper knowledgeMapper;

    @Override
    public PageBean queryAllKnowledge(PageBean pageBean) {

        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());

        PageInfo<Knowledge> pageInfo = new PageInfo<Knowledge>(knowledgeMapper.queryAllKnowledge());
        PageBean pb = new PageBean();
        pb.setTotalPages(pageInfo.getPages());
        pb.setPageSize(pageInfo.getPageSize());
        pb.setTotalCounts((int) pageInfo.getTotal());
        pb.setCurrentPage(pageInfo.getPageNum());
        pb.setContent(pageInfo.getList());
        return pb;
    }


    @Override
    public PageBean querySomeKnowledge(PageBean pageBean) {

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());

        PageInfo<Knowledge> pageInfo = new PageInfo<Knowledge>(knowledgeMapper.querySomeKnowledge());
        PageBean pb = new PageBean();
        pb.setTotalPages(pageInfo.getPages());
        pb.setPageSize(pageInfo.getPageSize());
        pb.setTotalCounts((int) pageInfo.getTotal());
        pb.setCurrentPage(pageInfo.getPageNum());
        pb.setContent(pageInfo.getList());
        return pb;
    }

    @Override
    public void addKnowledge(Knowledge k) {
        knowledgeMapper.addKnowledge(k);

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteKnowledge(String id) {
        knowledgeMapper.deleteKnowledge(id);

    }

    @Override
    public void updateKnowledge(String id,String kTitle,String createUserId,String kAnswer){
        knowledgeMapper.updateKnowledge(id,kTitle,createUserId,kAnswer);
    }

    @Override
    public void updateKnowledgeStatus(String id, String status) {
        knowledgeMapper.updateKnowledgeStatus(id,status);

    }

    @Override
    public Knowledge queryKnowledgeById(String id) {
        return knowledgeMapper.queryKnowledgeById(id);
    }

    @Override
    public void updateAppr(String id, String kApprUserId, String kApprMemo, Date kApprTime) {
        knowledgeMapper.updateAppr(id, kApprUserId, kApprMemo, kApprTime);
    }
}
