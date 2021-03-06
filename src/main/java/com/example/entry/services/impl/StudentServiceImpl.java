package com.example.entry.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entry.dao.IAdminUserDao;
import com.example.entry.dao.IStudentDao;
import com.example.entry.pojo.vo.AdminUserVO;
import com.example.entry.pojo.vo.StudentVO;
import com.example.entry.services.IStudentService;
import com.example.entry.util.CommonUtil;
import com.example.entry.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.entry.dao.IAdminUserDao.*;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/21 17:33
 * @Description:
 * @Version 1.0
 */
@Service
public class StudentServiceImpl  implements IStudentService {

    @Autowired
    IStudentDao studentDao;

    @Autowired
    IAdminUserDao adminUserDao;

    @Override
    public Map<String,Object> queryStudentList( String createId,String qq, int pageIndex, int pageSize) {
        AdminUserVO vo = adminUserDao.queryById(createId);
        Map<String,Object> map = new HashMap<>();

        List<StudentVO> vos =studentDao.queryStudentList(vo.getRole(),createId,qq, CommonUtil.getPageOffset(pageIndex,pageSize), pageSize);
        int pageCount = studentDao.countByQQ(vo.getRole(),createId,qq);
        map.put("data",vos);
        map.put("pageCount",pageCount);
        return map;
    }

    @Override
    public int del(String id) {
        return studentDao.delById(id);
    }

    @Override
    public int update(String id, String name, String phone, String weChat, String qq, String photoUrl) {
        return studentDao.updateStudent(id, name, phone, weChat, qq, photoUrl);
    }

    @Override
    public String add(String name, String phone, String weChat, String createId,String qq, String photoUrl)
    {
        StudentVO vo = new StudentVO();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        vo.setId(uuid);
        vo.setName(name);
        vo.setPhone(phone);
        vo.setWeChat(weChat);
        vo.setQq(qq);
        vo.setPhotoUrl(photoUrl);
        vo.setCreateId(createId);
         studentDao.add(vo);
        return ResultUtil.OK(vo);
    }
}
