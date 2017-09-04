package com.zh.framework.service;

import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;
import com.zh.framework.mapper.KnowledgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("knowledgeService")
public class KnowldegeServiceImpl extends BaseServiceImpl<Knowledge> implements KnowledgeService{

        @Autowired
        KnowledgeMapper knowledgeMapper;



}
