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
            if (param.get("sType") != null && !"".equals(param.get("sType"))) {
                WHERE("s.sType=#{sType}");
            }
            if (param.get("sParentId") != null && !"".equals(param.get("sParentId"))) {
                WHERE("s.sParentId=#{sParentId}");
            }
            if (param.get("sName") != null && !"".equals(param.get("sName"))) {
                WHERE("s.sName like '%"+param.get("sName")+"%'");
            }
            WHERE("s.deleteStatus =1");
            if (param.get("sidx") != null && !"".equals(param.get("sidx"))) {
                if("createUser".equals(((String)param.get("sidx")).replace(" ",""))){
                    ORDER_BY("crtuName" + " " + param.get("sord"));
                }else {
                    ORDER_BY("" + param.get("sidx") + " " + param.get("sord"));
                }
            }
        }}.toString();
        return sql;
    }

    public String checkRepeat(@Param("column")String column,@Param("value") String value){
        return new SQL(){{
            SELECT("COUNT(*)");
            FROM("tb_resource");
            WHERE(column+"=#{value}");
        }}.toString();
    }

    public String queryByPid(@Param("pid")String pid,
                             @Param("sidx")String sidx,
                             @Param("sord") String sord){
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
            WHERE("s.deleteStatus =1");
            if (sidx != null && !"".equals(sidx)) {
                if("createUser".equals(sidx)){
                    ORDER_BY("crtuName" + " " + sord);
                }else {
                    ORDER_BY("" + sidx + " " + sord);
                }
            }
        }}.toString();
    }

    public String queryByRole(@Param("roleid") String roleid,@Param("delStatus") int delStatus){
        return new SQL(){{
            SELECT("s.id as id," +
                    "s.sParentId as sParentId," +
                    "s.sName as sName," +
                    "s.sType," +
                    "s.sUrl," +
                    "s.sIcon," +
                    "s.sIndex");
            FROM("tb_resources_role sr");
            LEFT_OUTER_JOIN("tb_resource s on sr.sid = s.id");
            WHERE("sr.rid=#{roleid}");
            if(delStatus!=-1){
                WHERE("s.deleteStatus=#{delStatus}");
            }
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
            LEFT_OUTER_JOIN("tb_user u on ru.uid = u.id");
            LEFT_OUTER_JOIN("tb_role r on sr.rid=r.id");
            LEFT_OUTER_JOIN("tb_resource s on sr.sid = s.id");
            WHERE("ru.uid=#{userid}");
            WHERE("u.deleteStatus=1");
            WHERE("s.deleteStatus=1");
            WHERE("r.deleteStatus=1");
        }}.toString();
    }
}
