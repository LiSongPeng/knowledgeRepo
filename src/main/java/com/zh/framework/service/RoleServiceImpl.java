package com.zh.framework.service;

import com.zh.framework.entity.Role;
import com.zh.framework.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Letg4 on 2017/9/7.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleOption() {

        return roleMapper.queryRoleOption();
    }

    @Override
    public int setUserRole(String rid, List<String> sids) {
        int total=0;
        for (String sid:sids) {
            total+=roleMapper.setUserRole(rid,sid);
        }
        return total;
    }
}
