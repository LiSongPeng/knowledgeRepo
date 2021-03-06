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
    List<Resource> queryByPid(@Param("pid")String pid,
                              @Param("sidx")String sidx,
                              @Param("sord") String sord);

    @SelectProvider(type = ResourceSQL.class,method = "queryByUser")
    List<Resource> queryByUser(@Param("userid") String userid);

    @SelectProvider(type = ResourceSQL.class,method = "queryByRole")
    List<Resource> queryByRole(@Param("roleid") String roleid,@Param("delStatus") int delStatus);

    @SelectProvider(type = ResourceSQL.class, method = "checkRepeat")
    int checkRepeat(@Param("column")String column,@Param("value") String value);

}
