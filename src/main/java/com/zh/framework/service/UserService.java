package com.zh.framework.service;

import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.User;

import java.util.List;

/**
 * Created by Letg4 on 2017/9/4.
 */
public interface UserService {

    PageBean<User> query(PageBean<User> pageBean);

    int setUserRole(String uid, List<String> roleList);

    User login(String username, String password);
}
