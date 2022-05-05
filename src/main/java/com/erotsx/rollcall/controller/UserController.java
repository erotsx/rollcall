package com.erotsx.rollcall.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.erotsx.rollcall.entity.AjaxResult;
import com.erotsx.rollcall.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("/login")
    public AjaxResult login(String username, String password) {
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public AjaxResult register(String username, String password) {
        return userService.register(username, password);
    }

    @PostMapping("/logout")
    public String logout() {
        StpUtil.logout();
        return "登出成功";
    }
}
