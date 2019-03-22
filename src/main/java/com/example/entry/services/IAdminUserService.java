package com.example.entry.services;

public interface IAdminUserService {

    /**
     * 用户登录
     *
     * @param account
     * @param pwd
     * @return
     */
    String login(String account, String pwd) throws Exception;

    /**
     * 注册
     * @return
     */
    String register(String name, String account, String pwd) throws Exception;

    /**
     * 修改登录密码
     */
    String updatePwd(String account, String pwd, String newPwd) throws Exception;
}
