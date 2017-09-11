package com.zh.framework.interceptor;


import com.zh.framework.entity.Resource;
import com.zh.framework.service.ResourceService;
import com.zh.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Letg4 on 2017/9/10.
 */
public class UserAccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        String prefix="/knowledgeRepo";

        String getResUrl="/resource/getUserRes.form";

        String loginUrl="/user/login.form";

        String cuid=request.getHeader("Current-UserId");

        //放行OPTIONS请求
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }





        String reqUri=request.getRequestURI();
        System.out.println("cuid =  "+cuid);
        reqUri=reqUri.substring(prefix.length());
        System.out.println("reqUri =  "+reqUri);

        //放行登录请求
        if (reqUri.equals(loginUrl)){
            return true;
        }
        //登录验证
        if (cuid==null||cuid.equals("")){
            response.sendError(401,"未登录");
            return false;
        }
        //允许登录用户获取可用资源列表
        if (reqUri.equals(getResUrl)){
            return true;
        }

        List<Resource> userres= resourceService.queryByUser(cuid);
        for(Resource resource: userres){
            String resurl= resource.getsUrl();
            //资源url为空，直接进行下个判断
            if (resurl==null||"".equals(resurl)){
                System.out.println("空!!:"+resurl);
                continue;
            }
            //目的url 比 资源url短 直接进行下个判断
            if (reqUri.length()<resurl.length()){
                System.out.println("短!!:"+resurl);
                continue;
            }
            if (reqUri.substring(0, resurl.length()).equals(resurl)) {
                System.out.println("ok!!:"+resurl);
                return true;
            }
            System.out.println("错!!:"+resurl);
        }
        response.sendError(401,"用户没有权限进行操作");
        return false;
    }
}
