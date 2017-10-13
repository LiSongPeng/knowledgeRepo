package com.zh.framework.mapper;

import com.zh.framework.entity.User;
import com.zh.framework.sql.UserSQL;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/3.
 */
@Repository
public interface UserMapper {

    @SelectProvider(type = UserSQL.class, method = "query")
    @ResultMap("com.zh.framework.mapper.UserMapper.userMap")
    List<User> query(Map<String, Object> param);

    @SelectProvider(type = UserSQL.class, method = "checkRepeat")
    public int checkRepeat(@Param("column")String column,@Param("value") String value);

    @InsertProvider(type = UserSQL.class, method = "setUserRole")
    int setUserRole(@Param("uid") String uid,@Param("rid") String rid);

    @UpdateProvider(type = UserSQL.class,method = "updateLastOnline")
    int updateLastOnline(@Param("uid") String uid);

    @DeleteProvider(type = UserSQL.class,method = "clearUserRole")
    int clearUserRole( String uid);

    @SelectProvider(type = UserSQL.class, method = "selectById")
    @ResultMap("com.zh.framework.mapper.UserMapper.userMap")
    User selectById(String uid);

    @UpdateProvider(type = UserSQL.class,method = "update")
    public int update(@Param("id") String id,
                      @Param("attrs") Map<String,Object> attrs);


    User queryByNameAndPass(@Param("username") String username, @Param("password") String password);

    void updateLastLoginTime(@Param("id") String id, @Param("date") Date date);
}
