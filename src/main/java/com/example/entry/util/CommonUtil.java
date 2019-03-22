package com.example.entry.util;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/22 09:48
 * @Description:
 * @Version 1.0
 */
public class CommonUtil {

    public static int getPageOffset(int pageIndex,int pageSize) {
        if (pageIndex <= 0) {
            return 0;
        }
        return (pageIndex - 1) * pageSize;
    }
}
