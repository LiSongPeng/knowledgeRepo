package com.zh.framework.sql;

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
}
