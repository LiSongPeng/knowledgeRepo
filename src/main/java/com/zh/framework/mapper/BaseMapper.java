package com.zh.framework.mapper;

import com.zh.framework.entity.Knowledge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BaseMapper<T> {
    //@Select("select * from ${tableName}")
    public List<T> query(@Param("tableName") String tableName);

    public void delete(@Param("tableName") String tableName,@Param("id") int id);

    public void add(@Param("tableName") String tableName,@Param("entity") T entity);

    public void update(@Param("tableName") String tableName,@Param("entity") T entity);




}
