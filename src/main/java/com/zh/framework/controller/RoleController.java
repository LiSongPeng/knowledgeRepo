package com.zh.framework.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.framework.entity.Role;
import com.zh.framework.service.BaseService;
import com.zh.framework.service.ResourceService;
import com.zh.framework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    @GetMapping("/getResources.form")
    @ResponseBody
    public List<String> getResources(@RequestParam("roleId") String roleId) {

        return resourceService.getResources(roleId);
    }

    @RequestMapping("/getUserRole.form")
    @ResponseBody
    public List<String> getUserRole(@RequestParam("uid") String uid){
        return roleService.getUserRole(uid);
    }


    @RequestMapping("/setRoleRes.form")
    @ResponseBody
    public List<Integer> setRoleRes(@RequestParam("resIds") String resIds,@RequestParam("roleId") String roleId) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        JavaType javaType=mapper.getTypeFactory().constructCollectionType(ArrayList.class,String.class);
        List<String> list=mapper.readValue(resIds,javaType);
        roleService.clearRoleRes(roleId);
        int total= roleService.setRoleRes(roleId ,list);
        List<Integer> totallist=new ArrayList<>();
        totallist.add(total);
        return totallist;
    }
}
