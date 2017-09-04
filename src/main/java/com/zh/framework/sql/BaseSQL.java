package com.zh.framework.sql;

import com.zh.framework.util.GenericsUtils;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

public class BaseSQL {

    public String query(final Object entity)
    {
        Class eclass=entity.getClass();
        final String tableName="tb_"+eclass.getSimpleName().toLowerCase();
        Field[] f=null;
        f=eclass.getDeclaredFields();
        final Field[] finalF = f;
        return new SQL() {{
            try {
                SELECT("*");
                FROM(tableName);
                for (Field field: finalF){
                    if (field.get(entity)!=null){
                        WHERE(field.getName()+"=#{"+field.getName()+"}");
                    }
                }
            }catch (IllegalAccessException iae){
                System.out.println("base无法正确获取属性");
            }
        }}.toString();
    }
    public String delete(Object entity){
        Class eclass=entity.getClass();
        final String tableName="tb_"+eclass.getSimpleName().toLowerCase();
        return new SQL() {{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");

        }}.toString();
    }
    public String add(final Object entity){
        Class eclass=entity.getClass();
        final String tableName="tb_"+eclass.getSimpleName().toLowerCase();
        Field[] f=null;
        f=eclass.getDeclaredFields();
        final Field[] finalF = f;
        return new SQL(){{
            try {
                INSERT_INTO(tableName);
                for (Field field: finalF){
                    if (field.get(entity)!=null){
                        VALUES(field.getName(),"#{"+field.getName()+"}");
                    }
                }
            }catch (IllegalAccessException iae){
                System.out.println("base无法正确获取属性");
            }
        }}.toString();
    }
    public String update(final Object entity){
        Class eclass=entity.getClass();
        final String tableName="tb_"+eclass.getSimpleName().toLowerCase();
        Field[] f=null;
        f=eclass.getDeclaredFields();
        final Field[] finalF = f;
        return new SQL(){{
            try {
                UPDATE(tableName);
                for (Field field: finalF){
                    if (field.get(entity)!=null){
                        SET(field.getName(),"#{"+field.getName()+"}");
                    }
                }
                WHERE("id=#{id}");
            }catch (IllegalAccessException iae){
                System.out.println("base无法正确获取属性");
            }
        }}.toString();
    }
}
