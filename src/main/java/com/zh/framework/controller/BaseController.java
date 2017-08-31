package com.zh.framework.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.mapper.BaseMapper;
import com.zh.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public  class BaseController<T> {

    @Autowired
    //BaseMapper<T> service;

    BaseService<T> service;
    /**
     * 普通查询
     *
     * @param tableName 执行查询的表的名称
     *
     */
    public List<T> query(String tableName){
        List<T> list=service.query(tableName);
        return list;
    };

    /**
     * 分页查询
     *
     * @param pageNumber 请求的页码
     *
     */
    public PageBean<T> pagedQuery(int pageNumber){

       // PageBean aa=service.pagedQuery(tableName,pageNumber,pageSize);
        PageBean aa=null;

        return aa;

    };


    /**
     * 根据主键删除
     *
     * @param tableName 执行删除的表的名称
     * @param id 删除信息的主键
     *
     */

    public void delete(String tableName,String id){
        service.delete(tableName,id);

    };






}
