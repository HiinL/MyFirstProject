package com.example.entry.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName 用户表
 * @Auther: Administrator
 * @Date: 2019/3/21 16:15
 * @Description:
 * @Version 1.0
 */

@SuppressWarnings("serial")
@Data
@TableName("admin_user")
public class AdminUser implements Serializable {

    /**id*/
    @TableId(type = IdType.UUID)
    private String id;

    /**用户名*/
    private String name;

    /**账号*/
    private String account;

    /**密码*/
    private String pwd;

    /**创建时间*/
    private Date createTime;
}
