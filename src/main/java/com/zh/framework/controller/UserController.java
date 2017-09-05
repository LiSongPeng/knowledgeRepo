//package com.zh.framework.controller;
//
//import com.zh.framework.entity.PageBean;
//import com.zh.framework.entity.User;
//import com.zh.framework.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Letg4 on 2017/9/3.
// */
//
//@Controller
//@RequestMapping("/user")
//public class UserController extends BaseController<User>{
//
//    @Autowired
//    private UserService userService;
//
//    public UserController() {
//        super(new User());
//    }
//
//
//
//    //    @RequestMapping("/userList")
////    @ResponseBody
////    public void query(@RequestBody JSONObject jsonObj, HttpServletResponse response){
////
////        System.out.println(jsonObj.toJSONString());
////    }
//    @RequestMapping("/query.form")
//    public Map<String,Object> query(
//            @RequestParam("currentPage") int currentPage,
//            @RequestParam("pageSize") int pageSize,
//            @RequestParam("sord") String sord,
//            @RequestParam("sidx") String sidx
//    ){
//        PageBean<User> pageBean=new PageBean<User>();
//        pageBean.setCurrentPage(currentPage);
//        pageBean.setPageSize(pageSize);
//        pageBean.setSidx(sidx);
//        pageBean.setSord(sord);
//        Map<String,Object> jsmap=new HashMap<>();
//        PageBean<User> repb=userService.query(pageBean);
//        jsmap.put("totalPages",repb.getTotalPages());
//        jsmap.put("currentPages",repb.getCurrentPage());
//        jsmap.put("pageSize",repb.getPageSize());
//        jsmap.put("totalCounts",repb.getTotalCounts());
//        jsmap.put("content",repb.getContent());
//        return jsmap;
//    }
//
//}
