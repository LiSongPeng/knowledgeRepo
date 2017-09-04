package com.zh.framework.mapper;

import com.zh.framework.sql.BaseSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BaseMapper<T> {

    @SelectProvider(type = BaseSQL.class, method="query")
    public List<T> query(T entity);

    @DeleteProvider(type = BaseSQL.class,method = "delete")
    public void delete(T entity);



    @InsertProvider(type = BaseSQL.class,method = "add")
    public void add(@Param("entity") T entity);

    @UpdateProvider(type = BaseSQL.class,method = "update")
    public void update(@Param("entity") T entity);


}
