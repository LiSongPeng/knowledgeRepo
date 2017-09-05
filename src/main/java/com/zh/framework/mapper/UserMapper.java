package com.zh.framework.mapper;

import com.zh.framework.entity.User;
import com.zh.framework.sql.UserSQL;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/3.
 */
@Repository
public interface UserMapper {

    @SelectProvider(type = UserSQL.class,method = "query")
    @ResultMap("com.zh.framework.mapper.UserMapper.userMap")
    List<User> query(Map<String,Object> param);

    @InsertProvider(type = UserSQL.class,method = "setUserRole")
    int setUserRole(String uid,String rid);
}
