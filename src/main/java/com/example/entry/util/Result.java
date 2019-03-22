package com.example.entry.util;

import java.util.HashMap;
import java.util.Map;

public enum Result {

    /**
     * 请求成功
     */
    OK("200", "请求成功"),

    /**
     * 参数列表错误
     */
    PARAMS_ERROR("400", "参数列表错误"),

    NO_FOUND_PAGE("404", "找不到页面"),
    /**
     * 服务器异常
     */
    SERVER_ERROR("500", "服务器异常"),

    /**
     * 登录失败：帐号不存在
     */
    LOGIN_ACCOUNT_ERROR("2001", "帐号不存在"),
    /**
     * 登录失败：登录密码错误
     */
    LOGIN_PASSWORD_ERROR("2002", "密码错误"),
    /** */
    ACCOUNT_EXIST("2003", "注册异常，帐号已存在"),
    /**
     * 密码修改失败
     */
    UPDATE_PASSWORD_ERROR_UNCHANGED("2014", "修改密码失败，新旧密码不能一致"),
    /**
     * 密码修改失败
     */
    UPDATE_PASSWORD_ERROR("2015", "修改密码失败，请稍候再试");


    public String key;

    public String value;

    private Result(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + key + "," + value + ")";
    }

    public static Map<String, String> getMap() {
        Map<String, String> commonEnumMap = new HashMap<String, String>();
        for (Result s : Result.values()) {
            commonEnumMap.put(s.key, s.value);
        }
        return commonEnumMap;
    }
}
