package com.example.entry.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/22 09:07
 * @Description:
 * @Version 1.0
 */
public class MD5CoderUtils {

    /**
     * MD5消息摘要
     * @param data 待做摘要处理的数据
     * @return byte[] 消息摘要
     * @throws Exception
     */
    public static byte[] encodeMD5(String data) throws Exception{
        //执行消息摘要
        return DigestUtils.md5(data);
    }

    /**
     * MD5消息摘要
     * @param data 待做摘要处理的数据
     * @return 消息摘要
     * @throws Exception
     */
    public static String encodeMD5Hex(String data) throws Exception{
        //执行消息摘要
        return DigestUtils.md5Hex(data);
    }
}
