package com.zh.framework.controller;

import com.zh.framework.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Letg4 on 2017/9/7.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {

    public RoleController() {
        super(new Role());
    }
}
