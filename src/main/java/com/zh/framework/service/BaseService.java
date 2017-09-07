package com.zh.framework.service;

import com.zh.framework.entity.PageBean;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface  BaseService<T> {
    public PageBean<Map<String,Object>> query(PageBean<T> pageBean);

    public int delete(String tableName,String id);

    public int add(String tableName,Map<String,Object> attrs);

    public int update(String tableName,String id,Map<String,Object> attrs);

    public Map<String,Object> queryById(String id, String tableName);
}
