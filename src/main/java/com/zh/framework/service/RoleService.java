package com.zh.framework.service;

import com.zh.framework.entity.Role;

import java.util.List;

/**
 * Created by Letg4 on 2017/9/7.
 */
public interface RoleService {
    List<Role> queryRoleOption();
    int setRoleRes(String rid,List<String> sids);
    List<String> getUserRole(String uid);
    int clearRoleRes( String rid);
}
