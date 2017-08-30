package com.zh.framework.controller;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.Response;
import com.zh.framework.service.BaseService;
import com.zh.framework.util.Constant;
import com.zh.framework.util.TypeTester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class KnowledgeController extends BaseController<BaseService> {

    @GetMapping("/query.form")
    @ResponseBody
    public List<Knowledge> query() {
        List<Knowledge> list=service.query("tb_knowledge");
        service.delete("tb_knowledge",1);
        System.out.println("信息="+list.toString());
         return list;
    }

}
