package com.zh.framework.service;

import com.zh.framework.entity.PageBean;

import java.util.List;

public interface  BaseService<T> {
    public List<T> query(String tableName);

    public PageBean<T> pagedQuery(String tableName, int pageNumber, int pageSize);

    public void delete(String tableName,String id);

    public void add( String tableName,T entity);

    public void update(String tableName,T entity);


}
