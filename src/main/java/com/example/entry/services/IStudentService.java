package com.example.entry.services;

import java.util.Map;

public interface IStudentService {

    /**查询学员列表*/
    Map<String,Object> queryStudentList(String qq, int pageIndex, int pageSize);

    /**删除学员*/
    int del(String id);

    /**更新学员*/
    int update(String id,String name,String phone
            ,String weChat,String qq,String photoUrl);

    /**新增学员*/
    String add(String name, String phone, String weChat, String createId,String qq, String photoUrl);
}
