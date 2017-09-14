package com.zh.framework.service;

import com.zh.framework.entity.Role;
import com.zh.framework.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<Role> queryRoleList(Map<String,Object> param) {
        return roleMapper.queryRoleList(param);
    }

    @Override
    public int setRoleRes(String rid, List<String> sids) {
        int total=0;
        for (String sid:sids) {
            total+=roleMapper.setRoleRes(rid,sid);
        }
        return total;
    }

    @Override
    public List<String> getUserRole(String uid,int delStatus) {
        return roleMapper.getUserRole(uid,delStatus);
    }

    @Override
    public int clearRoleRes(String rid) {
        return roleMapper.clearRoleRes(rid);
    }


}
