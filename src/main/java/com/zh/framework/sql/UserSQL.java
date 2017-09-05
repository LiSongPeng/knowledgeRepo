package com.zh.framework.sql;

import com.zh.framework.entity.User;
import org.apache.ibatis.annotations.Mapper;
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
                    "u.createUserId as createUserId," +
                    "u.createTime as createTime," +
                    "u.uLastOnLine as uLastOnLine," +
                    "r.id as rId," +
                    "r.rName as rName, " +
                    "r.rDescription as rDescription");
            FROM("tb_user u");
            if (user.getId() != null && !"".equals(user.getId())) {
                WHERE("u.id=#{user.id}");
            }
            if (user.getuName() != null && !"".equals(user.getuName())) {
                WHERE("uName like '%"+user.getuName()+"%'");
            }
            LEFT_OUTER_JOIN("tb_role_user ru on u.id = ru.uid");
            LEFT_OUTER_JOIN("tb_role r on ru.rid = r.id");
            if (param.get("sidx") != null && !"".equals(param.get("sidx"))) {
                ORDER_BY("" + param.get("sidx") + " " + param.get("sord"));
            }
        }}.toString();
        System.out.println(sql);
        return sql;
    }

    public String setUserRole(Map<String,Object> param){
        return new SQL(){{
            INSERT_INTO("tb_role_user");
            VALUES("uid","#{uid}");
            VALUES("rid","#{rid}");
        }}.toString();
    }
}
