package com.zh.framework.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.User;
import com.zh.framework.mapper.BaseMapper;
import com.zh.framework.service.BaseService;
import com.zh.framework.util.GenericsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> query(
            @RequestParam("currentPage") int currentPage,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("sord") String sord,
            @RequestParam("sidx") String sidx
    ){
        PageBean<T> pageBean=new PageBean<T>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setSidx(sidx);
        pageBean.setSord(sord);
        if(pageBean.getContent()==null||pageBean.getContent().size()==0){
            pageBean.setContent(new ArrayList<T>());
            pageBean.getContent().add(this.target);
        }
        Map<String,Object> jsmap=new HashMap<>();
        PageBean<Map<String,Object>> repb=baseService.query(pageBean);
        jsmap.put("totalPages",repb.getTotalPages());
        jsmap.put("currentPages",repb.getCurrentPage());
        jsmap.put("pageSize",repb.getPageSize());
        jsmap.put("totalCounts",repb.getTotalCounts());
        jsmap.put("content",repb.getContent());
        return jsmap;
    }

    @RequestMapping("/update.form")
    public void update(@RequestBody T entity){
        baseService.update(entity);
    }

    @RequestMapping("/add.form")
    public void add(@RequestBody T entity){
        baseService.add(entity);
    }
    /**
     * 根据主键删除
     *
     * @param entity 要删除的实体条件信息
     *
     *
     */
    @RequestMapping("/delete.form")
    public void delete(@RequestBody T entity){
        baseService.delete(entity);
    }






}
