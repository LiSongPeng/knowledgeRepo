package com.zh.framework.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.Role;
import com.zh.framework.entity.User;
import com.zh.framework.service.BaseService;
import com.zh.framework.service.RoleService;
import com.zh.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Letg4 on 2017/9/3.
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    public UserController() {
        super(new User());
    }


    //    @RequestMapping("/userList")
//    @ResponseBody
//    public void query(@RequestBody JSONObject jsonObj, HttpServletResponse response){
//
//        System.out.println(jsonObj.toJSONString());
//    }
    @RequestMapping(value = "/query.form")
    public Map<String, Object> query(HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String sord = request.getParameter("sord");
        String sidx = request.getParameter("sidx");
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setSidx(sidx);
        pageBean.setSord(sord);
        if ("true".equals(request.getParameter("search"))) {
            User user = new User();
            user.setId(request.getParameter("id"));
            String uName=request.getParameter("uName");
            //按用户名查询时过滤前后空格
            if (uName!=null){
                uName=uName.replaceAll("^\\s*","").replaceAll("\\s*$","");
            }
            user.setuName(uName);
            List<User> ulist = new ArrayList<>();
            ulist.add(user);
            pageBean.setContent(ulist);
        }
        Map<String, Object> jsmap = new HashMap<>();
        PageBean<User> repb = userService.query(pageBean);
        jsmap.put("totalPages", repb.getTotalPages());
        jsmap.put("currentPage", repb.getCurrentPage());
        jsmap.put("pageSize", repb.getPageSize());
        jsmap.put("totalCounts", repb.getTotalCounts());
        jsmap.put("content", repb.getContent());
        return jsmap;
    }

    @Override
    @RequestMapping(value = "/delete.form")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        return super.updateDeleteStatus(id,0,response);
    }

    @RequestMapping(value = "/userAdd/add.form")
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uName=request.getParameter("uName");
        Map<String,Object> result=new HashMap<>();
        if (uName==null||"".equals(uName)){
            result.put("msg","用户名不能为空");
            return result;
        }
        if (userService.checkRepeat("uName",uName)>0){
            response.sendError(40011);
            result.put("msg","用户名已存在");
            return result;
        }
        return super.add(request);
    }

    @RequestMapping(value = "/userAdd/check.form")
    @ResponseBody
    public Map<String, Object> check(){
        Map<String,Object> result=new HashMap<>();
        result.put("msg","成功");
        return result;
    }


    @RequestMapping(value = "/userUpdate/update.form")
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uName=request.getParameter("uName");
        Map<String,Object> result=new HashMap<>();
        if (uName==null||"".equals(uName)){
            result.put("msg","用户名不能为空");
            response.sendError(40012);
            return result;
        }
        //检验用户名是否改变，没改不查重
        Map<String,Object> orgUser=super.queryById(request.getParameter("id"));
        if (!uName.equals(orgUser.get("uName"))){
            if (userService.checkRepeat("uName",uName)>0){
                response.sendError(40011);
                result.put("msg","用户名已存在");
                return result;
            }
        }
        return super.update(request);
    }


    @Override
    @RequestMapping("/userUpdate/queryById.form")
    @ResponseBody
    public Map<String, Object> queryById(@RequestParam("id") String id){
        return super.queryById(id);
    }


    @RequestMapping("/userRole/getUserRole.form")
    @ResponseBody
    public Map<String,Object> getUserRole(@RequestParam("uid") String uid){
        List<Role> list = roleService.queryRoleOption();
        List<String> userRolelist = roleService.getUserRole(uid,1);
        Map<String,Object> result=new HashMap<>();
        result.put("allRole",list);
        result.put("userRole",userRolelist);
        return result;
    }

    @RequestMapping("/userRole/setUserRole.form")
    @ResponseBody
    public Map<String, Object> setUserRole(@RequestParam("uid") String uid,@RequestParam("rids") String rids) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        JavaType javaType=mapper.getTypeFactory().constructCollectionType(ArrayList.class,String.class);
        List<String> list=mapper.readValue(rids,javaType);
        userService.clearUserRole(uid);
        int total=userService.setUserRole(uid,list);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        return map;
    }


    @RequestMapping(value = "/login.form", method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestParam("username") String username, @RequestParam("password") String password/*, HttpServletRequest request*/) {
        User user = userService.login(username, password);
//        request.getSession().setAttribute("currUser", user);
        return user;
    }

    @RequestMapping(value = "/userPass/checkPass.form")
    @ResponseBody
    public Map<String,Object> checkPass(HttpServletRequest request){
        String uid=request.getHeader("Current-UserId");
        String originPass=request.getParameter("originPassword");
        Map<String,Object> result=new HashMap<>();
        User curUser=userService.selectById(uid);
        System.out.println(originPass);
        if (originPass.equals(curUser.getuPassword())){
            result.put("valid",true);
            System.out.println("123");
        }else {
            result.put("valid",false);
            System.out.println("456");
        }
        return result;
    }

    @RequestMapping(value = "/resetUserPass.form")
    @ResponseBody
    public Map<String,Object> resetPass(HttpServletRequest request){
        String uid=request.getParameter("uid");
        Map<String,Object> attrs=new HashMap<>();
        attrs.put("uPassword","123456");
        Map<String, Object> result = new HashMap<>();
        if(userService.update(uid,attrs)>0) {
            result.put("result", "success");
        }else {
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping(value = "/userPass/modifyPass.form")
    @ResponseBody
    public Map<String,Object> modifyPass(HttpServletRequest request){
        String uid=request.getHeader("Current-UserId");
        String originPass=request.getParameter("originPassword");
        String newPass=request.getParameter("newPassword");
        Map<String,Object> result=new HashMap<>();
        User curUser=userService.selectById(uid);
        if (curUser.getuPassword().equals(originPass)){
            Map<String,Object> attrs=new HashMap<>();
            attrs.put("uPassword",newPass);
            System.out.println("hahaa");
            if(userService.update(uid,attrs)>0){
                result.put("valid",true);
            }else {
                result.put("valid",false);
            }
        }else {
            result.put("valid",false);
        }
        return result;
    }

    /*    @RequestMapping(value = "/quit.form")
    @ResponseBody
    public String quit(HttpServletRequest request) {
        request.getSession().setAttribute("currUser", null);
        return "SUCCESS";
    }*/
}
