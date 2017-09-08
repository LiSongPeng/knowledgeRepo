package com.zh.framework.mapper;

import com.zh.framework.entity.Role;
import com.zh.framework.sql.RoleSQL;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Letg4 on 2017/9/7.
 */
@Repository
public interface RoleMapper {
    @SelectProvider(type = RoleSQL.class,method = "queryRoleOption")
    public List<Role> queryRoleOption();

    @InsertProvider(type = RoleSQL.class,method = "setUserRole")
    public int setUserRole(@Param("rid") String rid, @Param("sid") String sid);
}
