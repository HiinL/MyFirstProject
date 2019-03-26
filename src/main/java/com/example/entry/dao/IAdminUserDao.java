package com.example.entry.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entry.pojo.vo.AdminUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IAdminUserDao extends BaseMapper<AdminUserVO> {

    AdminUserVO queryByAccount(@Param("account") String account);

    int delById(@Param("id") String id);

    int updateUser(@Param("id") String id, @Param("name") String name, @Param("pwd") String pwd
            , @Param("account") String account);

    int add(AdminUserVO vo);

    AdminUserVO queryById(@Param("id") String id);
}
