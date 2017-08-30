package com.zh.framework.service;

import com.zh.framework.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("baseService")
public class BaseServiceImpl implements BaseService{
    @Autowired
    private BaseMapper baseMapper;


    @Override
    public List query(String tableName) {
        return baseMapper.query(tableName) ;
    }

    @Override
    public void delete(String tableName, int id) {
        baseMapper.delete(tableName,id);
    }

    @Override
    public void add(String tableName, Object entity) {
        baseMapper.add(tableName,entity);

    }

    @Override
    public void update(String tableName, Object entity) {
        baseMapper.update(tableName,entity);

    }
}
