package com.zh.framework.mapper;

import com.zh.framework.sql.BaseSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BaseMapper<T> {

    @SelectProvider(type=BaseSQL.class, method="query")
    @ResultMap(value = "knowledgeMap")
    public List<T> query(String tableName);

    @DeleteProvider(type = BaseSQL.class,method = "delete")
    public void delete(String tableName,String  id);

   // public List pagedQuery(String tableName, int pageNumber,int pageSize);


    public void add(@Param("tableName") String tableName,@Param("entity") T entity);

    public void update(@Param("tableName") String tableName,@Param("entity") T entity);




}
