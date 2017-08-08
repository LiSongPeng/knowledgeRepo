package com.zh.framework.sql;

import com.zh.framework.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by Letg4 on 2017/9/5.
 */
public class ResourceSQL {
    public String query(Map<String,Object> param) {
        String sql = new SQL() {{
            SELECT("s.id as id," +
                    "s.sParentId as sParentId," +
                    "s.sName as sName," +
                    "s.sType as sType," +
                    "s.sUrl as sUrl," +
                    "s.sIcon as sIcon," +
                    "s.sIndex," +
                    "s.deleteStatus," +
                    "s.createTime," +
                    "u.id as crtuId," +
                    "u.uName as crtuName");
            FROM("tb_resource s");
            LEFT_OUTER_JOIN("tb_user u on s.createUserId=u.id");
            if (param.get("id") != null && !"".equals(param.get("id"))) {
                WHERE("s.id=#{id}");
            }
            if (param.get("sParentId") != null && !"".equals(param.get("sParentId"))) {
                WHERE("s.sParentId=#{sParentId}");
            }
            if (param.get("sName") != null && !"".equals(param.get("sName"))) {
                WHERE("s.sName like '%"+param.get("sName")+"%'");
            }
        }}.toString();
        return sql;
    }
    public String queryByPid(String pid){
        return new SQL(){{
            SELECT("s.id as id," +
                    "s.sParentId as sParentId," +
                    "s.sName as sName," +
                    "s.sType as sType," +
                    "s.sUrl as sUrl," +
                    "s.sIcon as sIcon," +
                    "s.sIndex as sIndex," +
                    "s.deleteStatus as deleteStatus," +
                    "s.createTime as createTime," +
                    "u.id as crtuId," +
                    "u.uName as crtuName");
            FROM("tb_resource s");
            LEFT_OUTER_JOIN("tb_user u on s.createUserId=u.id");
            if (pid == null ) {
                WHERE("s.sParentId is null");
            }else {
                WHERE("s.sParentId=#{pid}");
            }
        }}.toString();
    }

    public String queryByRole(@Param("roleid") String roleid){
        return new SQL(){{
            SELECT("s.id as id," +
                    "s.sName as sName");
            FROM("tb_resources_role sr");
            LEFT_OUTER_JOIN("tb_resource s on sr.sid = s.id");
            WHERE("sr.rid=#{roleid}");
        }}.toString();
    }
    public String queryByUser(@Param("userid") String userid){
        return new SQL(){{
            SELECT("s.id as id," +
                    "s.sParentId as sParentId," +
                    "s.sName as sName," +
                    "s.sType," +
                    "s.sUrl," +
                    "s.sIcon," +
                    "s.sIndex");
            FROM("tb_resources_role sr ");
            LEFT_OUTER_JOIN("tb_role_user ru on sr.rid = ru.rid");
            LEFT_OUTER_JOIN("tb_resource s on sr.sid = s.id");
            WHERE("ru.uid=#{userid}");
        }}.toString();
    }
}
