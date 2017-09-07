package com.zh.framework.mapper;

import com.zh.framework.entity.Resource;
import com.zh.framework.sql.ResourceSQL;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/6.
 */
@Repository
public interface ResourceMapper {
    @SelectProvider(type = ResourceSQL.class,method = "query")
    @ResultMap("com.zh.framework.mapper.ResourceMapper.resourceMap")
    List<Resource> query(Map<String,Object> param);

    @SelectProvider(type = ResourceSQL.class,method = "queryByPid")
    @ResultMap("com.zh.framework.mapper.ResourceMapper.resourceMap")
    List<Resource> queryByPid(String pid);

    @SelectProvider(type = ResourceSQL.class,method = "query")
    List<Resource> queryByRole(@Param("roleid") String roleid);
}
