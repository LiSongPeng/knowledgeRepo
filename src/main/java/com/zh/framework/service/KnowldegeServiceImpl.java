package com.zh.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;
import com.zh.framework.mapper.KnowledgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("knowledgeService")
//public class KnowldegeServiceImpl extends BaseServiceImpl<Knowledge> implements KnowledgeService{
public class KnowldegeServiceImpl implements KnowledgeService{
        @Autowired
        KnowledgeMapper knowledgeMapper;

        @Override
        public PageBean queryAllKnowledge(PageBean pageBean) {

                PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());

                PageInfo<Knowledge> pageInfo=new PageInfo<Knowledge>(knowledgeMapper.queryAllKnowledge());
                PageBean pb=new PageBean();
                pb.setTotalPages(pageInfo.getPages());
                pb.setPageSize(pageInfo.getPageSize());
                pb.setTotalCounts((int)pageInfo.getTotal());
                pb.setCurrentPage(pageInfo.getPageNum());
                pb.setContent(pageInfo.getList());
                return pb;
        }

        @Override
        public void addKnowledge(Knowledge k) {
                knowledgeMapper.addKnowledge(k);

        }

        @Override
        public void deleteKnowledge(String id) {
                knowledgeMapper.deleteKnowledge(id);

        }

        @Override
        public void updateKnowledge(Knowledge k) {

        }

        @Override
        public void updateKnowledgeStatus(String id, String status) {

        }






}
