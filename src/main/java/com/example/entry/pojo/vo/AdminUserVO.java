package com.example.entry.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.entry.pojo.AdminUser;
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
public class AdminUserVO extends AdminUser {
}
