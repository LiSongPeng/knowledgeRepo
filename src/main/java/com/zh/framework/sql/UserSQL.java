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
                    "u.deleteStatus as deleteStatus," +
                    "u.createTime as createTime," +
                    "u.uLastOnLine as uLastOnLine," +
                    "crtu.id as crtuId," +
                    "crtu.uName as crtuName" );
            FROM("tb_user u");
            if (user.getId() != null && !"".equals(user.getId())) {
                WHERE("u.id=#{user.id}");
            }
            if (user.getuName() != null && !"".equals(user.getuName())) {
                WHERE("u.uName like '%"+user.getuName()+"%'");
            }
            WHERE("u.deleteStatus=1");
            LEFT_OUTER_JOIN("tb_user crtu on u.createUserId=crtu.id");
            if (param.get("sidx") != null && !"".equals(param.get("sidx"))) {
                if("createUser".equals(((String)param.get("sidx")).replace(" ",""))){
                    ORDER_BY("crtuName" + " " + param.get("sord"));
                }else {
                    ORDER_BY("" + param.get("sidx") + " " + param.get("sord"));
                }
            }
        }}.toString();
        System.out.println(sql);
        return sql;
    }

    public String update(@Param("id") String id,
                         @Param("attrs") Map<String,Object> attrs){
        return new SQL(){{
            UPDATE("tb_user");
            for (Map.Entry<String,Object> attr: attrs.entrySet()){
                if (attr!=null){
                    SET(attr.getKey()+"=#{attrs."+attr.getKey()+"}");
                }
            }
            WHERE("id=#{id}");
        }}.toString();
    }

    public String selectById(String uid){
        return new SQL(){{
            SELECT("*");
            FROM("tb_user");
            WHERE("id=#{uid}");
        }}.toString();
    }

    public String checkRepeat(@Param("column")String column,@Param("value") String value){
        return new SQL(){{
            SELECT("COUNT(*)");
            FROM("tb_user");
            WHERE(column+"=#{value}");
        }}.toString();
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
