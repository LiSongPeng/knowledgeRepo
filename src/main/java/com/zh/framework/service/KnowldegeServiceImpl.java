package com.zh.framework.service;

import com.zh.framework.entity.Knowledge;
import com.zh.framework.mapper.KnowledgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("knowledgeService")
public class KnowldegeServiceImpl implements KnowledgeService {

        @Autowired
        KnowledgeMapper knowledgeMapper;


    @Override
    public void insertKnowledge(Knowledge k) {
//        knowledgeMapper.insertKnowledge(k);
    }




    @Override
    public List<Knowledge> queryKnowledgesAndSort(String orderBy) {
        return null;
    }

    @Override
    public Knowledge queryKnowledgeById(String id) {
        return null;
    }

    @Override
    public void updateUseCount(int count, String id) {

    }



    @Override
    public void updateKnowledgeStatus(String id, String status) {

    }

    @Override
    public void deleteKnowledge(String id) {

    }

    @Override
    public void addKnowledge(Knowledge k) {

    }
}
