package com.zh.framework.controller;

import com.zh.framework.entity.Resource;
import com.zh.framework.entity.TreeGridData;
import com.zh.framework.entity.User;
import com.zh.framework.service.ResourceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/6.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController<Resource>{
    @Autowired
    ResourceService resourceService;

    public ResourceController(){
        super(new Resource());
    }

    @RequestMapping("/query.form")
    @ResponseBody
    public Map<String,Object> query(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String sidx=request.getParameter("sidx");
        String sord=request.getParameter("sord");
        map.put("content",resourceService.queryAsTree(sidx,sord));
        return map;
    }

    @RequestMapping(value = {"/resourceAdd/getResOptions.form","/resourceUpdate/getResOptions.form"})
    @ResponseBody
    public Map<String,Object> getResOptions(){
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("sType",0);
        List<Resource> reslist= resourceService.querySearch(param);
        List<Map<String,String>> allres=new ArrayList<>();
        for(Resource res : reslist){
            Map<String,String> resmap=new HashMap<>();
            resmap.put("id",res.getId());
            resmap.put("text",res.getsName());
            allres.add(resmap);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("resOptions",allres);
        return result;
    }

    @RequestMapping("/resourceAdd/add.form")
    @ResponseBody
    public Map<String,Object> add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sName=request.getParameter("sName");
        Map<String,Object> result=new HashMap<>();
        if (sName==null||"".equals(sName)){
            result.put("msg","资源名不能为空");
            response.sendError(40012);
            return result;
        }
        if (resourceService.checkRepeat("sName",sName)>0){
            response.sendError(40011);
            result.put("msg","资源名已存在");
            return result;
        }
        return super.add(request);
    }

    @RequestMapping("/resourceUpdate/selectById.form")
    @ResponseBody
    public Resource selectById(@RequestParam("id") String id){
        Map<String,Object> param=new HashMap<>();
        param.put("id",id);
        return resourceService.querySearch(param).get(0);
    }

    @RequestMapping("/resourceUpdate/update.form")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sName=request.getParameter("sName");
        Map<String,Object> result=new HashMap<>();
        if (sName==null||"".equals(sName)){
            result.put("msg","资源名不能为空");
            return result;
        }
        // 检验资源名是否改变，没改则不用查重
        Map<String,Object> param=new HashMap<>();
        param.put("id",request.getParameter("id"));
        Resource orgRes= resourceService.querySearch(param).get(0);
        if (!orgRes.getsName().equals(sName)){
            if (resourceService.checkRepeat("sName",sName)>0){
                response.sendError(40011);
                result.put("msg","资源名已存在");
                return result;
            }
        }
        return super.update(request);
    }


    @RequestMapping("/getUserRes.form")
    @ResponseBody
    public List<Resource> getUserRes(HttpServletRequest request){
        String userid =request.getHeader("Current-UserID");
        return resourceService.queryByUser(userid);
    }

    @Override
    @RequestMapping(value = "/delete.form")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        return super.updateDeleteStatus(id,0,response);
    }
}
