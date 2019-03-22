package com.example.entry.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName 学员表
 * @Auther: Administrator
 * @Date: 2019/3/21 16:15
 * @Description:
 * @Version 1.0
 */

@SuppressWarnings("serial")
@Data
@TableName("student")
public class Student implements Serializable {

    /**id*/
    @TableId(type = IdType.UUID)
    private String id;

    /**用户名*/
    private String name;

    /**电话*/
    private String phone;

    /**密码*/
    private String pwd;

    /**微信*/
    private String weChat;

    /**QQ*/
    private String qq;

    /**图片地址*/
    private String photoUrl;

    /**创建时间*/
    private Date createTime;

    /**创建人id*/
    private String createId;
}
