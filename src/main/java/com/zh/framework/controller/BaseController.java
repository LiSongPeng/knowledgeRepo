package com.zh.framework.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.mapper.BaseMapper;
import com.zh.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public  class BaseController<T> {

    @Autowired
    //BaseMapper<T> service;

    private BaseService<T> service;
    /**
     * 普通查询
     *
     * @param pageBean 查询的信息。包括分页等。
     *
     */
    public PageBean<T> query(PageBean<T> pageBean){

        return service.query(pageBean);
    }


    public void update(T entity){
        service.update(entity);
    }

    public void add(T entity){
        service.add(entity);
    }
    /**
     * 根据主键删除
     *
     * @param entity 要删除的实体条件信息
     *
     *
     */

    public void delete(T entity){
        service.delete(entity);

    }






}
