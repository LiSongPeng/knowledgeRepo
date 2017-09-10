package com.zh.framework.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

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

    public String queryRoleList(Map<String,Object> param){
        return new SQL(){{
            SELECT("r.id," +
                    "r.rName," +
                    "r.deleteStatus," +
                    "r.rDescription," +
                    "r.createTime," +
                    "u.id as crtuId," +
                    "u.uName as crtuName");
            FROM("tb_role r");
            LEFT_OUTER_JOIN("tb_user u on r.createUserId = u.id");
            if(param.get("rName")!=null&&!"".equals(param.get("rName"))){
                WHERE("r.rName like '%"+param.get("rName")+"%'");
            }
        }}.toString();
    }

    public String clearRoleRes(@Param("rid") String rid){
        return new SQL(){{
            DELETE_FROM("tb_resources_role");
            WHERE("rId=#{rid}");
        }}.toString();
    }

    public String setRoleRes(@Param("rid") String rid,@Param("sid") String sid){
        return new SQL(){{
            INSERT_INTO("tb_resources_role");
            VALUES("rId","#{rid}");
            VALUES("sId","#{sid}");
        }}.toString();
    }

    public String getUserRole(@Param("uid") String uid){
        return new SQL(){{
            SELECT("rid");
            FROM("tb_role_user");
            WHERE("uId=#{uid}");
        }}.toString();
    }

    public String clearUserRole( String uid){
        return new SQL(){{
            DELETE_FROM("tb_role_user");
            WHERE("uId=#{uid}");
        }}.toString();
    }


}
