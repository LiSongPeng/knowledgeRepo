package com.zh.framework.mapper;

import com.zh.framework.entity.Role;
import com.zh.framework.sql.RoleSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/7.
 */
@Repository
public interface RoleMapper {
    @SelectProvider(type = RoleSQL.class,method = "queryRoleOption")
    public List<Role> queryRoleOption();
    @SelectProvider(type = RoleSQL.class,method = "getUserRole")
    public List<String> getUserRole(@Param("uid") String uid,@Param("delStatus") int delStatus);

    @SelectProvider(type = RoleSQL.class,method = "queryRoleList")
    @ResultMap("com.zh.framework.mapper.RoleMapper.roleMap")
    public List<Role> queryRoleList(Map<String,Object> param);

    @DeleteProvider(type = RoleSQL.class,method = "clearRoleRes")
    public int clearRoleRes(@Param("rid") String rid);
    @InsertProvider(type = RoleSQL.class,method = "setRoleRes")
    public int setRoleRes(@Param("rid") String rid, @Param("sid") String sid);
}
