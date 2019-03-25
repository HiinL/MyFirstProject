package com.example.entry.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/25 09:58
 * @Description:
 * @Version 1.0
 */
public class AliyunOssClientUtil {
    //阿里云API的外网域名
    public static final String ENDPOINT = "ENDPOINT";
    //阿里云API的密钥Access Key ID
    public static final String ACCESS_KEY_ID = "KEY_ID";
    //阿里云API的密钥Access Key Secret
    public static final String ACCESS_KEY_SECRET = "KEY_SECRET";
    //阿里云API的bucket名称
    public static final String BACKET_NAME = "BACKET_NAME";
    //阿里云oss-图片位置
    public static final String FILE_IMG="img/";
    //阿里云API的文件夹名称
    public static final String FOLDER="PicFace/face/";
    //阿里云oss-视频位置
//    public static final String FILE_VIDEO="video/";
    //阿里云oss-文件位置(pdf\word等)
    public static final String FILE="file/";

    public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }
    private static String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png"
                .equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    private static void uploadFile2OSSTest(InputStream instream, String fileName,
                                           OSSClient ossClient) {
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            ossClient.putObject(BACKET_NAME, FOLDER + fileName, instream, objectMetadata);
        } catch (IOException e) {
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //上传一张图片
    public static String saveImg(MultipartFile multipartFile, OSSClient ossClient){
        // 获取图片名字
        String fileName = multipartFile.getOriginalFilename();
        //防止名字冲突覆盖原有图片
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        fileName=uuid+fileName;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            AliyunOssClientUtil.uploadFile2OSSTest(inputStream, fileName,ossClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  urlName = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length());
        return "https://" + BACKET_NAME+"."+ENDPOINT+ File.separator + FOLDER +urlName;
    }
}
