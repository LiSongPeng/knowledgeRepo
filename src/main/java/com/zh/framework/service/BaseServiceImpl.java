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

    /**
     * 普通查询
     *
     * @param tableName 执行查询的表的名称
     *
     */
    @Override
    public List query(String tableName) {
        return baseMapper.query(tableName) ;
    }
    /**
     * 分页查询
     *
     * @param pageNumber 请求的页码
     * @param tableName  要操作的表
     * @param pageSize 单页存放数据的条数
     *
     */
    @Override
    public PageBean pagedQuery(String tableName, int pageNumber, int pageSize) {

        PageBean aa=new PageBean();
        PageHelper.startPage(pageNumber,pageSize);
        //List list =baseMapper.query(tableName);
        PageInfo  p=new PageInfo(baseMapper.query(tableName));
        System.out.println(p);
        aa.setRecords(p.getSize());
        aa.setTotal(p.getPages());
        aa.setPage(p.getPageNum());
        aa.setRows(baseMapper.query(tableName));

        return aa;
    }

    /**
     * 根据主键删除
     *
     * @param tableName 执行删除的表的名称
     * @param id 删除信息的主键
     *
     */

    @Override
    public void delete(String tableName, String id) {
        baseMapper.delete(tableName,id);
    }

    @Override
    public void add(String tableName, Object entity) {
        //baseMapper.add(tableName,entity);

    }

    @Override
    public void update(String tableName, Object entity) {
        //baseMapper.update(tableName,entity);

    }
}
