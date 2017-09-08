package com.zh.framework.service;

import com.zh.framework.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Letg4 on 2017/9/7.
 */
public interface RoleService {
    List<Role> queryRoleOption();
    int setUserRole(String rid,List<String> sids);
}
