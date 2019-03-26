package com.example.entry.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entry.pojo.vo.StudentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IStudentDao extends BaseMapper<StudentVO> {

    List<StudentVO> queryStudentList(@Param("role") Integer role,@Param("createId") String createId,@Param("qq") String qq, @Param("offset") int offset, @Param("size") int size);

    int delById(@Param("id") String id);

    int updateStudent(@Param("id") String id, @Param("name") String name, @Param("phone") String phone
            , @Param("weChat") String weChat, @Param("qq") String qq, @Param("photoUrl") String photoUrl);

    int add(StudentVO vo);

    int countByQQ(@Param("role") Integer role,@Param("createId") String createId,@Param("qq") String qq);
}
