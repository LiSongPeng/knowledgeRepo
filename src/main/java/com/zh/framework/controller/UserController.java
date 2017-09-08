package com.zh.framework.controller;

import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.User;
import com.zh.framework.service.BaseService;
import com.zh.framework.service.RoleService;
import com.zh.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Letg4 on 2017/9/3.
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;


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
            user.setuName(request.getParameter("uName"));
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
    @RequestMapping(value = "/add.form")
    @ResponseBody
    public String add(HttpServletRequest request){
        List<String> list=new ArrayList<>();
        String userid= super.add(request);
        String rolestr= request.getParameter("uRole");
        String[] rolearr=rolestr.split(",");
        Collections.addAll(list,rolearr);

        userService.setUserRole(userid,list);
        return userid;
    }

    @Override
    @RequestMapping(value = "/update.form")
    @ResponseBody
    public String update(HttpServletRequest request){
        List<String> list=new ArrayList<>();
        String userid= super.add(request);
        String rolestr= request.getParameter("uRole");
        String[] rolearr=rolestr.split(",");
        Collections.addAll(list,rolearr);
        userService.setUserRole(request.getParameter("id"),list);
        return super.update(request);
    }


    @RequestMapping(value = "/login.form", method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestParam("username") String username, @RequestParam("password") String password/*, HttpServletRequest request*/) {
        User user = userService.login(username, password);
//        request.getSession().setAttribute("currUser", user);
        return user;
    }
/*    @RequestMapping(value = "/quit.form")
    @ResponseBody
    public String quit(HttpServletRequest request) {
        request.getSession().setAttribute("currUser", null);
        return "SUCCESS";
    }*/
}
