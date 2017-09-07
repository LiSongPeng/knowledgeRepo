package com.zh.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.mapper.BaseMapper;
import com.zh.framework.util.GenericsUtils;
import com.zh.framework.util.PageInfoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("baseService")
public class BaseServiceImpl<T> implements BaseService<T>{
    @Autowired
    private BaseMapper<T> baseMapper;

    private T target;

    public BaseServiceImpl() {
    }

    @Override
    public PageBean<Map<String,Object>> query(PageBean<T> pageBean) {
        Map<String,Object> param=new HashMap<String, Object>() ;
        T entity=pageBean.getContent().get(0);
        String tbname=entity.getClass().getSimpleName();
        tbname=("tb_"+tbname).toLowerCase();
        System.out.println("!!!!11111"+tbname);
        param.put("entity",entity);
        param.put("tableName",tbname);
        param.put("sidx",pageBean.getSidx());
        param.put("sord",pageBean.getSord());
        PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<>(baseMapper.query(param));
        PageInfoConvertor<Map<String,Object>> picvt=new PageInfoConvertor<>(pageInfo);
        return picvt.toPageBean();
    }

    @Override
    public int delete(String tableName,String id) {
        return baseMapper.delete(tableName,id);
    }

    @Override
    public int add(String tableName,Map<String,Object> attrs) {
        if (attrs.get("id")==null||"".equals(attrs.get("id"))){
            String uuid=UUID.randomUUID().toString();
            attrs.put("id",uuid );
        }
       return baseMapper.add(tableName,attrs);
    }

    @Override
    public int update(String tableName,String id,Map<String,Object> attrs) {
        return baseMapper.update(tableName, id, attrs);
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
