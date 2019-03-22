package com.example.entry.controller;

import com.example.entry.services.IStudentService;
import com.example.entry.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/21 17:26
 * @Description: 学员接口
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    IStudentService studentService;

    /**
     * 新增学员
     * @param name
     * @param phone
     * @param weChat
     * @param createId
     * @return
     */
    @PostMapping("addStudent")
    public String addStudent( @RequestParam("name") String name
            , @RequestParam("phone") String phone
            , @RequestParam("weChat") String weChat
            , @RequestParam("createId") String createId){

        return studentService.add(name,phone,weChat,createId);
    }

    /**
     * 删除学员
     * @param id
     * @return
     */
    @PostMapping("delStudent")
    public String delStudent( @RequestParam("id") String id ){

        return ResultUtil.OK(studentService.del(id));
    }

    /**
     * 更新学员
     * @param id
     * @param name
     * @param phone
     * @param weChat
     * @param qq
     * @param photoUrl
     * @return
     */
    @PostMapping("updateStudent")
    public String updateStudent( @RequestParam("id") String id,
                                 @RequestParam(value = "name",required = false) String name,@RequestParam(value ="phone",required = false) String phone,
                                 @RequestParam(value ="weChat",required = false) String weChat, @RequestParam(value ="qq",required = false) String qq,@RequestParam(value ="photoUrl",required = false) String photoUrl){
        return ResultUtil.OK(studentService.update(id, name, phone, weChat, qq, photoUrl));
    }

    /**
     * 查询学员列表
     * @param qq
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @PostMapping("querryList")
    public String querryList(
            @RequestParam("qq") String qq
    ,@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize") int pageSize){

        return ResultUtil.OK(studentService.queryStudentList(qq,pageIndex,pageSize));
    }
}
