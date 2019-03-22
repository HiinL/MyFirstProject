package com.example.entry.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/22 08:58
 * @Description:
 * @Version 1.0
 */
public class ArrayUtils {

    private static final String EMPTY_STRING = "";
    /**
     * 判断list是否有长度
     * <pre>
     * ArrayUtils.hasLength(new ArrayList()) = false;
     * ArrayUtils.hasLength(new ArrayList(){{add(1);add(2)}}) = true;
     * ArrayUtils.hasLength(null) = false;
     * </pre>
     * @param collection
     * @return true/false
     */
    public static boolean hasLength(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 判断数组是否有长度
     * <pre>
     * ArrayUtils.hasLength(new Object[]) = false;
     * ArrayUtils.hasLength(new Object[]{1,2,3}) = true;
     * ArrayUtils.hasLength(null) = false;
     * </pre>
     * @param collection
     * @return true/false
     */
    public static boolean hasLength(Object[] collection) {
        return collection != null && collection.length > 0;
    }

    /**
     * 连接数组
     * <pre>
     * ArrayUtils.join(new Object(){1,2,3},",")="1,2,3";
     * ArrayUtils.join(null,",")="";
     * ArrayUtils.join(new Object(){1,null,2,3},",")="1,2,3";
     * </pre>
     * @param array list
     * @param separator 分隔符
     * @return string
     */
    public static String join(Object[] array, String separator) {
        if (!hasLength(array)) {
            return EMPTY_STRING;
        } else {
            StringBuffer buff = new StringBuffer();
            for (Object obj:array) {
                if(obj == null || StringUtils.emptyOrNull(obj.toString())) continue;
                buff.append(obj.toString()).append(separator);
            }
            return buff.length() > 0 ? buff.toString().substring(0, buff.toString().length() - separator.length()) : EMPTY_STRING;
        }
    }

    /**
     * 合并两个String数组,并去除重复对象
     * @param array1
     * @param array2
     * @return
     */
    public static String[] merge(String[] array1, String[] array2) {
        Set<Object> set = new HashSet<Object>();
        if(hasLength(array1)) {
            set.addAll(Arrays.asList(array1));
        }
        if(hasLength(array2)) {
            set.addAll(Arrays.asList(array2));
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 连接list
     * <pre>
     * ArrayUtils.join(new ArrayList(),",")="";
     * ArrayUtils.hasLength(new ArrayList(){{add(1);add(2)}}) = "1,2";
     * ArrayUtils.join(null,",")="";
     * ArrayUtils.join(new ArrayList(){{add(1);add(null);add(3)}},",")="1,3";
     * </pre>
     */
    public static String join(Collection<?> list, String separator) {
        if (!hasLength(list)) {
            return EMPTY_STRING;
        } else {
            StringBuffer buff = new StringBuffer();
            for (Object obj:list) {
                if(obj == null) continue;
                buff.append(obj.toString()).append(separator);
            }
            return buff.toString().substring(0, buff.toString().length() - separator.length());
        }
    }

    public static boolean has(Object[] array,Object obj) {
        if(array == null || array.length ==0) return false;
        if(obj == null) return false;
        for(int i=0;i<array.length;i++) {
            if(obj.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串数组转Long数组
     * <pre>
     * 		ArrayUtils.longArray(new String[]{"1"}) = new Long[]{1}
     * </pre>
     * @param str
     * @return
     */
    public static Long[] longArray(String[] str) {
        Long[] longs = null;
        if (str.length > 0) {
            longs = new Long[str.length];
            for (int i = 0; i < str.length; i++) {
                if (str[i].matches("\\d+")) {
                    longs[i] = Long.parseLong(str[i]);
                }
            }
        }
        return longs;
    }

    public static boolean length(Collection<?> list, int length) {
        if(hasLength(list) && list.size() == length) {
            return true;
        } else if(!hasLength(list) && length == 0) {
            return true;
        }
        return false;
    }

    public static boolean length(Object[] array, int length) {
        if(hasLength(array) && array.length == length) {
            return true;
        } else if(!hasLength(array) && length == 0) {
            return true;
        }
        return false;
    }
}
