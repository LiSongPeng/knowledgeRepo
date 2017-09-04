package com.zh.framework.mapper;

import com.zh.framework.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Letg4 on 2017/9/3.
 */
@Repository
public interface UserMapper {

    List<User> query(User user,String orderby);

}
