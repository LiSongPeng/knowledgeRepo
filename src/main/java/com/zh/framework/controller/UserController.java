package com.zh.framework.controller;

import com.zh.framework.entity.PageBean;
import com.zh.framework.entity.User;
import com.zh.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/edit.form")
    @ResponseBody
    public String edit(HttpServletRequest request) {
        String oper = request.getParameter("oper");
        String remsg = "";
        switch (oper) {
            case "del":
                remsg = delete(request);
                break;
            case "add":
                remsg = add(request);
                break;
            case "edit":
                remsg = update(request);
                break;
            default:
                remsg = "操作有误";
        }
        return remsg;
    }

    @RequestMapping(value = "/login.form", method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        User user = userService.login(username, password);
        request.getSession().setAttribute("currUser", user);
        return user;
    }

    @RequestMapping(value = "/quit.form")
    @ResponseBody
    public String quit(HttpServletRequest request) {
        request.getSession().setAttribute("currUser", null);
        return "SUCCESS";
    }
}
