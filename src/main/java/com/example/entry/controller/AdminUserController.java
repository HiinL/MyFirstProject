package com.example.entry.controller;

import com.example.entry.services.IAdminUserService;
import com.example.entry.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/21 17:25
 * @Description:
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class AdminUserController {

    @Autowired
    IAdminUserService adminUserService;

    /**
     * 登陆
     * @param account
     * @param pwd
     * @return
     * @throws Exception
     */
    @PostMapping("login")
    public String login(
            @RequestParam("account") String account,
            @RequestParam("pwd") String pwd ) throws Exception {

        return adminUserService.login(account,pwd);
    }

    /**
     * 注册
     * @param name
     * @param account
     * @param pwd
     * @return
     * @throws Exception
     */
    @PostMapping("register")
    public String register( @RequestParam("name") String name
            , @RequestParam("account") String account
            , @RequestParam("pwd") String pwd) throws Exception {
        return adminUserService.register(name,account,pwd);
    }

    /**
     * 修改密码
     * @param account
     * @param pwd
     * @param newPwd
     * @return
     * @throws Exception
     */
    @PostMapping("updatePwd")
    public String updatePassword(@RequestParam("account") String account
            , @RequestParam("pwd") String pwd
            , @RequestParam("newPwd") String newPwd
    ) throws Exception {
        if (pwd.equals(newPwd)) {
            return ResultUtil.UPDATE_PASSWORD_ERROR_UNCHANGED();
        }
        return adminUserService.updatePwd(account, pwd, newPwd);
    }
}
