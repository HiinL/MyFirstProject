package com.example.entry.controller;

import com.aliyun.oss.OSSClient;
import com.example.entry.services.IStudentService;
import com.example.entry.util.AliyunOssClientUtil;
import com.example.entry.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/25 10:07
 * @Description:
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    IStudentService studentService;

    @RequestMapping(value = "/uploadStudentPicFace")
    public String uploadStudentPicFaceToOss(MultipartFile file, String id){
        OSSClient ossClient = AliyunOssClientUtil.getOSSClient();
        String url = AliyunOssClientUtil.saveImg(file, ossClient);
        //组织对象
        studentService.update(id, null, null, null, null, url);

        return ResultUtil.OK();
    }
}
