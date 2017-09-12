package com.zh.framework.sql;

import com.zh.framework.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by Letg4 on 2017/9/3.
 */
public class UserSQL {
    public String query(Map<String,Object> param) {
        String sql = new SQL() {{
            User user=(User)param.get("user");
            SELECT("u.id as id," +
                    "u.uName as uName," +
                    "u.uPassword as uPassword," +
                    "u.uDescription as uDescription," +
                    "u.deleteStatus as uDeleteStatus," +
                    "u.createTime as createTime," +
                    "u.uLastOnLine as uLastOnLine," +
                    "crtu.id as crtuId," +
                    "crtu.uName as crtuName," +
                    "r.id as rId," +
                    "r.rName as rName" );
            FROM("tb_user u");
            if (user.getId() != null && !"".equals(user.getId())) {
                WHERE("u.id=#{user.id}");
            }
            if (user.getuName() != null && !"".equals(user.getuName())) {
                WHERE("uName like '%"+user.getuName()+"%'");
            }
            LEFT_OUTER_JOIN("tb_user crtu on u.createUserId=crtu.id");
            LEFT_OUTER_JOIN("tb_role_user ru on u.id = ru.uid");
            LEFT_OUTER_JOIN("tb_role r on ru.rid = r.id");
            if (param.get("sidx") != null && !"".equals(param.get("sidx"))) {
                ORDER_BY("" + param.get("sidx") + " " + param.get("sord"));
            }
        }}.toString();
        System.out.println(sql);
        return sql;
    }

    public String updateLastOnline(@Param("uid") String uid){
        return new SQL(){{
            UPDATE("tb_user");
            SET("uLastOnline = now()");
            WHERE("id=#{uid}");
        }}.toString();
    }

    public String setUserRole(@Param("uid") String uid,@Param("rid") String rid){
        return new SQL(){{
            INSERT_INTO("tb_role_user");
            VALUES("uid","#{uid}");
            VALUES("rid","#{rid}");
        }}.toString();
    }

    public String clearUserRole(String uid){
        return new SQL(){{
            DELETE_FROM("tb_role_user");
            WHERE("uid=#{uid}");
        }}.toString();
    }
}
