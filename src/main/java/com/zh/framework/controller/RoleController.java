package com.zh.framework.controller;

import com.zh.framework.entity.Role;
import com.zh.framework.service.BaseService;
import com.zh.framework.service.ResourceService;
import com.zh.framework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Letg4 on 2017/9/7.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {

    @Autowired
    RoleService roleService;
    private ResourceService resourceService;

    @Resource(name = "resourceService")
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public RoleController() {
        super(new Role());
    }

    @RequestMapping("/getRoleOption.form")
    @ResponseBody
    public List<Role> getRoleOption() {
        StringBuffer sbuf = new StringBuffer();
        List<Role> list = roleService.queryRoleOption();
        return list;
    }

    @GetMapping("getResources.form")
    @ResponseBody
    public List<String> getResources(@RequestParam("roleId") String roleId) {
        return resourceService.getResources(roleId);
    }
}
