package com.zh.framework.controller;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.Response;
import com.zh.framework.service.BaseService;
import com.zh.framework.service.KnowledgeService;
import com.zh.framework.util.Constant;
import com.zh.framework.util.TypeTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class KnowledgeController extends BaseController<Knowledge> {

    @Autowired
    KnowledgeService knowledgeService;

    @GetMapping("/query.form")
    @ResponseBody
    @Override
    public List<Knowledge> query(String tableName) {

        List<Knowledge> list = service.query("tb_knowledge");

        System.out.println(list.toString());

        return list;
    }

    @GetMapping("/delete.form")
    @ResponseBody
    @Override
    public void delete(String tableName,String id) {


        service.delete("tb_knowledge","2");

        System.out.println("delete successful!!");

    }

    @GetMapping("/pagedQuery.form")
    @ResponseBody
    @Override
    public PageBean pagedQuery(@RequestParam("pageNumber")int pageNumber) {

        PageBean aa=service.pagedQuery("tb_knowledge",pageNumber,2);
        System.out.println(aa);
        return aa;



    }



//
//    @GetMapping("/update.form")
//    @ResponseBody
//    public void updateKnowledge(Knowledge k) {
//
//        knowledgeService.updateKnowledge(k);
//
//    }


}
