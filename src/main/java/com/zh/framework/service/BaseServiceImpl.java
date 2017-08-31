package com.zh.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.Knowledge;
import com.zh.framework.entity.PageBean;
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
    public PageBean pagedQuery(String tableName, int pageNumber, int pageSize) {

        PageBean aa=new PageBean();
        PageHelper.startPage(pageNumber,pageSize);
        //List list =baseMapper.query(tableName);
        PageInfo  p=new PageInfo(baseMapper.query(tableName));
        aa.setTotalPages(p.getPages());
        aa.setPageSize(p.getPageSize());
        aa.setCurrentPage(p.getPageNum());
        aa.setContent(baseMapper.query(tableName));

        return aa;
    }

    @Override
    public void delete(String tableName, String id) {
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
