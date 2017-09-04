package com.zh.framework.service;

import com.zh.framework.entity.PageBean;

public interface  BaseService<T> {
    public PageBean<T> query(PageBean<T> pageBean);

    public void delete(T entity);

    public void add(T entity);

    public void update(T entity);


}
