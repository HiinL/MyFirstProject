package com.example.entry.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.entry.pojo.Student;
import lombok.Data;

/**
 * @ClassName 学员VO
 * @Auther: Administrator
 * @Date: 2019/3/21 16:15
 * @Description:
 * @Version 1.0
 */

@SuppressWarnings("serial")
@Data
@TableName("student")
public class StudentVO extends Student {

    /**创建时间*/
    private String createDate;

    /**创建人*/
    private String createName;
}
