package com.zh.framework.sql;

import org.apache.ibatis.jdbc.SQL;

public class BaseSQL {

    public String query(final String tableName)
    {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);

        }}.toString();
    }

    public String delete(final String tableName,String  id){

        return new SQL() {{
            DELETE_FROM(tableName);
            WHERE("id="+id);

        }}.toString();

    }

}
