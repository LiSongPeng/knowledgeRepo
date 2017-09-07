package com.zh.framework.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.User;
import com.zh.framework.mapper.BaseMapper;
import com.zh.framework.service.BaseService;
import com.zh.framework.util.GenericsUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.*;

public  class BaseController<T> {

    //BaseMapper<T> service;

    private T target;

    public BaseController(){
    }

    public BaseController(T target) {
        this.target = target;
    }

    @Autowired
    private BaseService<T> baseService;
    /**
     * 普通查询
     *
     * @param
     *
     */

    @RequestMapping("/query.form")
    @ResponseBody
    public Map<String,Object> query(HttpServletRequest request){
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        String sord=request.getParameter("sord");
        String sidx=request.getParameter("sidx");
        PageBean<T> pageBean=new PageBean<T>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setSidx(sidx);
        pageBean.setSord(sord);
        pageBean.setContent(new ArrayList<T>());
        pageBean.getContent().add(this.target);
        Map<String,Object> jsmap=new HashMap<>();
        PageBean<Map<String,Object>> repb=baseService.query(pageBean);
        jsmap.put("totalPages",repb.getTotalPages());
        jsmap.put("currentPage",repb.getCurrentPage());
        jsmap.put("pageSize",repb.getPageSize());
        jsmap.put("totalCounts",repb.getTotalCounts());
        jsmap.put("content",repb.getContent());
        return jsmap;
    }

    @RequestMapping("/queryById.form")
    @ResponseBody
    public Map<String, Object> queryById(@RequestParam("id") String id){
        return baseService.queryById(id,this.getTableName());
    }

    @RequestMapping("/update.form")
    @ResponseBody
    public String update(HttpServletRequest request){
        Enumeration em = request.getParameterNames();
        String id=request.getParameter("id");
        Map<String,Object> attrs=new HashMap<>();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            if ("id".equals(name)||"oper".equals(name))
                continue;
            String value = request.getParameter(name);
            attrs.put(name,value);
        }
        int total=0;
        total+=baseService.update(this.getTableName(),id,attrs);
        return ""+total;
    }

    @RequestMapping("/add.form")
    @ResponseBody
    public String add(HttpServletRequest request){
        Enumeration em = request.getParameterNames();
        Map<String,Object> attrs=new HashMap<>();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            System.out.println("name:name");
            if ("oper".equals(name))
                continue;
            String value = request.getParameter(name);
            attrs.put(name,value);
        }
        Date crttime=new Date(System.currentTimeMillis());
        attrs.put("createTime",crttime);
        int total=baseService.add(this.getTableName(),attrs);
        return ""+total;
    }

    @RequestMapping("/delete.form")
    @ResponseBody
    public String delete(HttpServletRequest request){
        String id = request.getParameter("id");
        int total=0;
        String[] idlist=id.split(",");
        for (String delid:idlist) {
            total+=baseService.delete(this.getTableName(),delid);
        }
        return ""+total;
    }

    public String getTableName(){

        String tableName="tb_"+this.target.getClass().getSimpleName();
        return tableName.toLowerCase();
    }





}
