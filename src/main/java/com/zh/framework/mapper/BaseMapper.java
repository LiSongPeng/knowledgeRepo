package com.zh.framework.mapper;

import com.zh.framework.sql.BaseSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseMapper<T> {

    @SelectProvider(type = BaseSQL.class, method="query")
    public List<Map<String,Object>> query(Map<String,Object> param);

    @DeleteProvider(type = BaseSQL.class,method = "delete")
    public int delete(@Param("tableName") String tableName,
                       @Param("id") String id);



    @InsertProvider(type = BaseSQL.class,method = "add")
    public int add(@Param("tableName") String tableName,
                    @Param("attrs") Map<String,Object> attrs);

    @UpdateProvider(type = BaseSQL.class,method = "update")
    public int update(@Param("tableName") String tableName,
                       @Param("id") String id,
                       @Param("attrs") Map<String,Object> attrs);


}
