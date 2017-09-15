package com.zh.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.User;
import com.zh.framework.mapper.UserMapper;
import com.zh.framework.util.PageInfoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        User user=pageBean.getContent().get(0);
        String uName=user.getuName();
        //按用户名查询时过滤前后空格
        if (uName!=null){
            uName=uName.replaceAll("^\\s*","").replaceAll("\\s*$","");
        }
        user.setuName(uName);
        map.put("user", user);
        map.put("sidx", pageBean.getSidx());
        map.put("sord", pageBean.getSord());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.query(map));
        PageInfoConvertor<User> piconvert = new PageInfoConvertor<>(pageInfo);
        return piconvert.toPageBean();
    }

    @Override
    public int setUserRole(String uid, List<String> roleList) {
        userMapper.clearUserRole(uid);
        int totalsuc = 0;
        for (String rid : roleList) {
            totalsuc += userMapper.setUserRole(uid, rid);
        }
        return totalsuc;
    }

    @Override
    public int checkRepeat(String column, String value) {
        return userMapper.checkRepeat(column,value);
    }

    @Override
    public int clearUserRole(String uid) {
        return userMapper.clearUserRole(uid);
    }

    @Override
    public int updateLastOnline(String uid) {
        return userMapper.updateLastOnline(uid);
    }


    @Override
    public User login(String username, String password) {
        User user = userMapper.queryByNameAndPass(username, password);
        if (user != null)
            userMapper.updateLastLoginTime(user.getId(), new Date());
        return user;
    }
}
