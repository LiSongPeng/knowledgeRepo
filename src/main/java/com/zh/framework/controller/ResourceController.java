package com.zh.framework.controller;

import com.zh.framework.entity.Resource;
import com.zh.framework.entity.TreeGridData;
import com.zh.framework.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/6.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController<Resource>{
    @Autowired
    ResourceService resourceService;

    public ResourceController(){
        super(new Resource());
    }

    @RequestMapping("/query.form")
    @ResponseBody
    public Map<String,Object> query(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        map.put("content",resourceService.queryAsTree());
        return map;
    }

    @RequestMapping("/selectById.form")
    @ResponseBody
    public Resource queryById(@RequestParam("id") String id){

        Map<String,Object> param=new HashMap<>();
        param.put("id",id);
        return resourceService.querySearch(param).get(0);
    }
}
