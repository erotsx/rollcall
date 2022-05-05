package com.erotsx.rollcall.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.erotsx.rollcall.dao.UserDao;
import com.erotsx.rollcall.entity.AjaxResult;
import com.erotsx.rollcall.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserService")
public class UserService {

    @Resource
    private UserDao userDao;

    public AjaxResult register(String username, String password) {
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            AjaxResult.doError("用户名或密码不能为空");
        }
        if (username.length() < 3 || username.length() > 20) {
            AjaxResult.doError("用户名长度应在3-20之间");
        }
        if (password.length() < 8 || password.length() > 20) {
            AjaxResult.doError("密码长度应在8-20之间");
        }
        if (userDao.findByUserName(username) != null) {
            AjaxResult.doError("用户名已存在");
        }
        SysUser user = new SysUser(username, password);
        userDao.register(user);
        Integer id = user.getId();
        userDao.setRole(id);
        return AjaxResult.doSuccess("注册成功");
    }

    public AjaxResult login(String username, String password) {
        SaTokenInfo saTokenInfo;
        JSONObject res = new JSONObject();
        SysUser user = userDao.findByUserName(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                StpUtil.login(user.getId());
                saTokenInfo = StpUtil.getTokenInfo();
                res.putOpt("username", username);
                res.putOpt("id", user.getId());
                res.putOpt("isLogin", saTokenInfo.isLogin);
                res.putOpt("tokenName", saTokenInfo.tokenName);
                res.putOpt("tokenValue", saTokenInfo.tokenValue);
                return AjaxResult.doSuccess("登录成功", res);
            } else {
                return AjaxResult.doError("密码错误");
            }
        } else {
            return AjaxResult.doError("用户名不存在");
        }
    }
}
