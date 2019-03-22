package com.example.entry.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/22 08:54
 * @Description:
 * @Version 1.0
 */
public class ResultUtil {

    /**
     * 返回json字符串
     *
     * @param data    数据体
     * @param status  状态码
     * @param message 状态描述
     * @return
     */
    public static String toJSON(Object data, String status, String message) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", data);
        resultMap.put("status", status);
        resultMap.put("message", message);
        return JSONUtils.toJSONString(resultMap);
    }

    /**
     * 主要是PC端分页使用
     *
     * @param data 当前查询结果
     * @param size 数据量
     * @return
     */
    public static String toListJson(Object data, int size) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", data);
        resultMap.put("size", size);
        resultMap.put("status", Result.OK.key);
        resultMap.put("message", Result.OK.value);
        return JSONUtils.toJSONString(resultMap);
    }

    /**
     * 返回json字符串
     *
     * @param data
     * @param result 结果集枚举
     * @return
     */
    public static String toJSON(Object data, Result result) {
        return toJSON(data, result.key, result.value);
    }

    /**
     * 返回json字符串
     *
     * @param result 结果集枚举
     * @return
     */
    public static String toJSON(Result result) {
        return toJSON(null, result.key, result.value);
    }

    public static String OK(Object data) {
        return toJSON(data, Result.OK.key, Result.OK.value);
    }

    public static String OK() {
        return toJSON(null, Result.OK.key, Result.OK.value);
    }

    public static String ERROR() {
        return toJSON(null, Result.SERVER_ERROR.key, Result.SERVER_ERROR.value);
    }

    public static String LOGIN_ACCOUNT_ERROR() {
        return toJSON(null, Result.LOGIN_ACCOUNT_ERROR.key, Result.LOGIN_ACCOUNT_ERROR.value);
    }

    public static String LOGIN_PASSWORD_ERROR() {
        return toJSON(null, Result.LOGIN_PASSWORD_ERROR.key, Result.LOGIN_PASSWORD_ERROR.value);
    }

    public static String ACCOUNT_EXIST() {
        return toJSON(null, Result.ACCOUNT_EXIST.key, Result.ACCOUNT_EXIST.value);
    }

    public static String UPDATE_PASSWORD_ERROR_UNCHANGED() {
        return toJSON(null, Result.UPDATE_PASSWORD_ERROR_UNCHANGED.key, Result.UPDATE_PASSWORD_ERROR_UNCHANGED.value);
    }

    public static String UPDATE_PASSWORD_ERROR() {
        return toJSON(null, Result.UPDATE_PASSWORD_ERROR.key, Result.UPDATE_PASSWORD_ERROR.value);
    }
}
