package com.zh.framework.service;

import com.zh.framework.entity.PageBean;

import java.util.Map;

public interface  BaseService<T> {
    public PageBean<Map<String,Object>> query(PageBean<T> pageBean);

    public void delete(T entity);

    public void add(T entity);

    public void update(T entity);


}
