package com.zh.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.User;
import com.zh.framework.mapper.UserMapper;
import com.zh.framework.util.PageInfoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/2.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean<User> query(PageBean<User> pageBean) {
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        if (pageBean.getContent() == null)
            pageBean.setContent(new ArrayList<>());
        if (pageBean.getContent().size() == 0)
            pageBean.getContent().add(new User());
        Map<String, Object> map = new HashMap<>();
        map.put("user", pageBean.getContent().get(0));
        map.put("sidx", pageBean.getSidx());
        map.put("sord", pageBean.getSord());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.query(map));
        PageInfoConvertor<User> piconvert = new PageInfoConvertor<>(pageInfo);
        return piconvert.toPageBean();
    }

    @Override
    public int setUserRole(String uid, List<String> roleList) {
        int totalsuc = 0;
        for (String rid : roleList) {
            totalsuc += userMapper.setUserRole(uid, rid);
        }
        return totalsuc;
    }

    @Override
    public User login(String username, String password) {
        return userMapper.queryByNameAndPass(username, password);
    }
}
