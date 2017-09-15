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
import javax.servlet.http.HttpServletResponse;
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
        if (rName!=null){
            rName=rName.replaceAll("^\\s*","").replaceAll("\\s*$","");
        }
        param.put("rName",rName);
        param.put("sidx",request.getParameter("sidx"));
        param.put("sord",request.getParameter("sord"));
        Map<String,Object> result=new HashMap<>();
        result.put("content",roleService.queryRoleList(param));
        return result;
    }

    @RequestMapping("/roleAdd/add.form")
    @ResponseBody
    public Map<String,Object> add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rName=request.getParameter("rName");
        Map<String,Object> result=new HashMap<>();
        if (rName==null||"".equals(rName)){
            result.put("msg","角色名不能为空");
            response.sendError(40012);
            return result;
        }
        if (roleService.checkRepeat("rName",rName)>0){
            result.put("msg","角色名已存在");
            response.sendError(40011);
            return result;
        }
        return super.add(request);
    }

    @RequestMapping("/roleAdd/check.form")
    @ResponseBody
    public Map<String,Object> check(){
        Map<String,Object> result=new HashMap<>();
        result.put("msg","成功");
        return result;
    }


    @Override
    @RequestMapping("/roleUpdate/query.form")
    @ResponseBody
    public Map<String,Object> query(HttpServletRequest request){
        return super.query(request);
    }

    @RequestMapping("/roleUpdate/update.form")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rName=request.getParameter("rName");
        Map<String,Object> result=new HashMap<>();
        if (rName==null||"".equals(rName)){
            result.put("msg","角色名不能为空");
            return result;
        }
        //检验用户名是否改变，没改不查重
        Map<String,Object> orgUser=super.queryById(request.getParameter("id"));
        if (!rName.equals(orgUser.get("rName"))){

        }
        if (roleService.checkRepeat("rName",rName)>0){
            response.sendError(40011);
            result.put("msg","角色名已存在");
            return result;
        }
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

    @Override
    @RequestMapping(value = "/delete.form")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        return super.updateDeleteStatus(id,0,response);
    }
}
