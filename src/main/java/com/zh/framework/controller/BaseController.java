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
import java.lang.reflect.Field;
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
    public Map<String,Object> update(HttpServletRequest request){
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
        Map<String,Object> result=new HashMap<>();
        result.put("total",total);
        return result;
    }

    @RequestMapping("/add.form")
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request){
        Enumeration em = request.getParameterNames();
        Field[] fields = target.getClass().getDeclaredFields();
        List<String> attrnames=new ArrayList<>();
        for(Field field:fields){
            attrnames.add(field.getName());
        }
        Map<String,Object> attrs=new HashMap<>();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            if (attrnames.contains(name)) {
                String value = request.getParameter(name);
                System.out.println(name+":::"+value);
                attrs.put(name, value);
            }
        }
        attrs.put("createUserId",request.getHeader("Current-UserId"));
        Date crttime=new Date(System.currentTimeMillis());
        attrs.put("createTime",crttime);
        String total=baseService.add(this.getTableName(),attrs);
        Map<String,Object> result=new HashMap<>();
        result.put("total",total);
        return result;
    }

    @RequestMapping("/delete.form")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request){
        String id = request.getParameter("id");
        int total=0;
        String[] idlist=id.split(",");
        for (String delid:idlist) {
            total+=baseService.delete(this.getTableName(),delid);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("total",total);
        return result;
    }

    public String getTableName(){

        String tableName="tb_"+this.target.getClass().getSimpleName();
        return tableName.toLowerCase();
    }





}
