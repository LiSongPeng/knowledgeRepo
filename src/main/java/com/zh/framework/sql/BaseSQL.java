package com.zh.framework.sql;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BaseSQL {



    public String selectWhitparam(Map<String,Object> param){

        return new SQL() {{
            SELECT("*");
            FROM("wwww");
//            for (Map.Entry<String,Object> entry : param) {
//                WHERE(entry.getKey()+"=#{"+entry.getKey()+"}");
//            }

                if(param.get("id")!=null){
                    WHERE("id=#{id}");
                }
            if(param.get("kTitle")!=null){
                WHERE("kTitle=#{kTitle}");
            }

            if(param.get("kAnswer")!=null){
                WHERE("kAnswer=#{kAnswer}");
            }


            if(param.get("kUseCount")!=null){
                WHERE("kUseCount=#{kUseCount}");
            }
            if(param.get("kUseTimeLast")!=null){
                WHERE("kUseTimeLast=#{kUseTimeLast}");
            }

            if(param.get("kApprStatus")!=null){
                WHERE("kApprStatus=#{kApprStatus}");
            }
            if(param.get("kApprUserId")!=null){
                WHERE("kApprUserId=#{kApprUserId}");
            }
            if(param.get("kApprTime")!=null){
                WHERE("kApprTime=#{kApprTime}");
            }
            if(param.get("kApprMemo")!=null){
                WHERE("id=#{kApprMemo}");
            }
            if(param.get("createUserId")!=null){
                WHERE("id=#{createUserId}");
            }
            if(param.get("createTime")!=null){
                WHERE("id=#{createTime}");
            }

        }}.toString();
//            Select()
//                    <if test="k.kTitle!=null">
//            k.kTitle=#
//
//            {
//                kTitle
//            },
//        </if>
//        <if test="k.kAnswer!=null">
//            k.kAnswer=#
//
//            {
//                kAnswer
//            },
//        </if>
//        <if test="k.kApprStatus!=null">
//            k.kApprStatus=#
//
//            {
//                kApprStatus
//            },
//        </if>
//        <if test="k.kApprUserId!=null">
//            k.kApprUserId=#
//
//            {
//                kApprUserId
//            },
//        </if>
//        <if test="k.kApprTime!=null">
//            k.kApprTime=#
//
//            {
//                kApprTime
//            },
//        </if>
//        <if test="k.kApprMemo!=null">
//            k.kApprMemo=#
//
//            {
//                kApprMemo
//            },
//        </if>
//        <if test="k.createUserId!=null">
//            k.createUserId=#
//
//            {
//                createUserId
//            },
//        </if>
//        <if test="k.createTime!=null">
//            k.createTime=#
//
//            {
//                createTime
//            }
//        </if>
//    </update>








    };



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
