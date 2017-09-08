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

    public String clearRoleRes(@Param("rid") String rid){
        return new SQL(){{
            DELETE_FROM("tb_resources_role");
            WHERE("rid=#{rid}");
        }}.toString();
    }

    public String setRoleRes(@Param("rid") String rid,@Param("sid") String sid){
        return new SQL(){{
            INSERT_INTO("tb_resources_role");
            VALUES("rid","#{rid}");
            VALUES("sid","#{sid}");
        }}.toString();
    }

    public String getUserRole(@Param("uid") String uid){
        return new SQL(){{
            SELECT("rid");
            FROM("tb_role_user");
            WHERE("uid=#{uid}");
        }}.toString();
    }

    public String clearUserRole( String uid){
        return new SQL(){{
            DELETE_FROM("tb_role_user");
            WHERE("uid=#{uid}");
        }}.toString();
    }


}
