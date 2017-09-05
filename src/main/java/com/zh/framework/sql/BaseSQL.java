package com.zh.framework.sql;

import com.zh.framework.util.GenericsUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.Map;

public class BaseSQL {

    public String query(final Map<String,Object> param)
    {
        return new SQL() {{
            try {
                SELECT("*");
                FROM(""+param.get("tableName"));
                Object entity=param.get("entity");
                if (param.get("entity")!=null) {
                    Class eclass = entity.getClass();
                    String tableName = "tb_" + eclass.getSimpleName().toLowerCase();
                    Field[] f = null;
                    f = eclass.getDeclaredFields();
                    Field[] finalF = f;
                    for (Field field : finalF) {
                        field.setAccessible(true);
                        if (field.get(entity) != null) {
                            WHERE(field.getName() + "=#{entity." + field.getName() + "}");
                        }
                    }
                }
                if (param.get("sidx")!=null&&!"".equals(param.get("sidx")))
                    ORDER_BY(""+param.get("sidx")+" "+param.get("sord"));
            }catch (IllegalAccessException iae){
                iae.printStackTrace();
                System.out.println("base无法正确获取属性");
            }
        }}.toString();
    }
    public String delete(@Param("tableName") String tableName,
                         @Param("id") String id){
        return new SQL() {{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String add(@Param("tableName") String tableName,
                      @Param("attrs") Map<String,Object> attrs){
        return new SQL(){{
            INSERT_INTO(tableName);
            for (Map.Entry<String,Object> attr: attrs.entrySet()){
                if (attr!=null){
                    VALUES(attr.getKey(),"#{attrs."+attr.getKey()+"}");
                }
            }
        }}.toString();
    }
    public String update(@Param("tableName") String tableName,
                         @Param("id") String id,
                         @Param("attrs") Map<String,Object> attrs){
        return new SQL(){{
            UPDATE(tableName);
            for (Map.Entry<String,Object> attr: attrs.entrySet()){
                if (attr!=null){
                    SET(attr.getKey(),"#{attrs."+attr.getKey()+"}");
                }
            }
            WHERE("id=#{id}");
        }}.toString();
    }
}
