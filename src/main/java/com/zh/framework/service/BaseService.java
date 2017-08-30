package com.zh.framework.service;

import java.util.List;

public interface  BaseService<T> {
    public List<T> query(String tableName);

    public void delete(String tableName,int id);

    public void add( String tableName,T entity);

    public void update(String tableName,T entity);


}
