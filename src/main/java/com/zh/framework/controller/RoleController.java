package com.zh.framework.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.framework.entity.Role;
import com.zh.framework.service.ResourceService;
import com.zh.framework.service.RoleService;
import com.zh.framework.entity.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;



    public RoleController() {
        super(new Role());
    }


    @RequestMapping("/queryList.form")
    @ResponseBody
    public Map<String,Object> queryList(HttpServletRequest request) {
        Map<String,Object> param=new HashMap<>();
        String rName=request.getParameter("rName");
        param.put("rName",rName);
        Map<String,Object> result=new HashMap<>();
        result.put("content",roleService.queryRoleList(param));
        return result;
    }

    @Override
    @RequestMapping("/roleUpdate/query.form")
    @ResponseBody
    public Map<String,Object> query(HttpServletRequest request){
        return super.query(request);
    }
    @Override
    @RequestMapping("/roleUpdate/update.form")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request){
        return super.update(request);
    }


    @GetMapping("/roleAuth/getResources.form")
    @ResponseBody
    public Map<String, Object> getResources(@RequestParam("roleId") String roleId) {
        List<Resource> reslist= resourceService.querySearch(new HashMap<String,Object>());
        List<Map<String,String>> allres=new ArrayList<>();
        for(Resource res : reslist){
            Map<String,String> resmap=new HashMap<>();
            resmap.put("sId",res.getId());
            resmap.put("sName",res.getsName());
            resmap.put("sParentId",res.getsParentId());
            allres.add(resmap);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("allRes",allres);
        result.put("roleRes",resourceService.getResources(roleId));
        return result;
    }



    @RequestMapping("/roleAuth/setRoleRes.form")
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
