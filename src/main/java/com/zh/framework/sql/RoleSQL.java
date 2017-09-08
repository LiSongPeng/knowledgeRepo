package com.zh.framework.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Letg4 on 2017/9/7.
 */
public class RoleSQL {
    public String queryRoleOption(){
        return new SQL(){{
            SELECT("id,rName");
            FROM("tb_role");
        }}.toString();
    }

    public String setUserRole(@Param("rid") String rid,@Param("sid") String sid){
        return new SQL(){{
            INSERT_INTO("tb_resources_role");
            VALUES("rid","#{rid}");
            VALUES("sid","#{sid}");
        }}.toString();
    }
}
