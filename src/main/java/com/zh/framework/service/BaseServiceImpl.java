package com.zh.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.mapper.BaseMapper;
import com.zh.framework.util.GenericsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("baseService")
public class BaseServiceImpl<T> implements BaseService<T>{
    @Autowired
    private BaseMapper<T> baseMapper;


    @Override
    public PageBean<T> query(PageBean<T> pageBean) {
        PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
        Map<String,Object> param=new HashMap<String, Object>() ;
        T entity = null;
        if(pageBean.getContent()!=null&&pageBean.getContent().size()>0){
            entity = pageBean.getContent().get(0);
        }
        String tbname="tb_"+GenericsUtils.getSuperClassGenricType(pageBean.getClass()).getSimpleName();
        param.put("entity",entity);
        param.put("tableName",tbname);
        param.put("sidx",pageBean.getSidx());
        param.put("sord",pageBean.getSord());
        PageInfo<T> pageInfo=new PageInfo<T>(baseMapper.query(param));
        PageBean<T> pb=new PageBean<T>();
        pb.setTotalPages(pageInfo.getPages());
        pb.setPageSize(pageInfo.getPageSize());
        pb.setCurrentPage(pageInfo.getPageNum());
        pb.setContent(pageInfo.getList());
        return pb;
    }

    @Override
    public void delete(T entity) {
        baseMapper.delete(entity);
    }

    @Override
    public void add(T entity) {
        baseMapper.add(entity);
    }

    @Override
    public void update(T entity) {
        baseMapper.update(entity);
    }


//    @Override
//    public List query(String tableName) {
//        return baseMapper.query(tableName) ;
//    }
//
//    @Override
//    public PageBean pagedQuery(String tableName, int pageNumber, int pageSize) {
//
//        PageBean pb=new PageBean();
//        PageHelper.startPage(pageNumber,pageSize);
//        //List list =baseMapper.query(tableName);
//        PageInfo  p=new PageInfo(baseMapper.query(tableName));
//        pb.setTotalPages(p.getPages());
//        pb.setPageSize(p.getPageSize());
//        pb.setCurrentPage(p.getPageNum());
//        pb.setContent(baseMapper.query(tableName));
//
//        return pb;
//    }
//
//    @Override
//    public void delete(String tableName, String id) {
//        baseMapper.delete(tableName,id);
//    }
//
//    @Override
//    public void add(String tableName, Object entity) {
//        baseMapper.add(tableName,entity);
//
//    }
//
//    @Override
//    public void update(String tableName, Object entity) {
//        baseMapper.update(tableName,entity);
//
//    }
}
