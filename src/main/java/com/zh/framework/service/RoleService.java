package com.zh.framework.service;

import com.zh.framework.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/7.
 */
public interface RoleService {
    List<Role> queryRoleOption();
    List<Role> queryRoleList(Map<String,Object> param);
    int setRoleRes(String rid,List<String> sids);
    List<String> getUserRole(String uid,int delStatus);
    int clearRoleRes( String rid);
}
