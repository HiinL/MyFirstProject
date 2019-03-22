package com.example.entry.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/22 08:59
 * @Description:
 * @Version 1.0
 */
public class StringUtils {

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DecimalFormat numberFormat = new DecimalFormat("###,###.##");

    /**
     * 判断字符串是否包含有意义字符
     *
     * <pre>
     * StringUtils.hasText(&quot; &quot;) = false;
     * StringUtils.hasText(&quot;test&quot;) = true;
     * StringUtils.hasText(null) = false;
     * StringUtils.hasText(&quot;  test&quot;) = true;
     * </pre>
     *
     * @param str
     * @return true/false
     */
    public static boolean hasText(String str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为空或null(去除空格)
     *
     * <pre>
     * StringUtils.emptyOrNull(&quot; &quot;) = true;
     * StringUtils.emptyOrNull(null) = true;
     * StringUtils.emptyOrNull(&quot;test&quot;) = false;
     * StringUtils.emptyOrNull(&quot; test&quot;) = false;
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean emptyOrNull(String str) {
        return str == null || !hasText(str);
    }

    /**
     * 判断字符串是否有长度
     *
     * <pre>
     * StringUtils.hasLength(&quot; &quot;) = true;
     * StringUtils.hasLength(&quot;&quot;) = false;
     * StringUtils.hasLength(&quot;abc&quot;) = true;
     * StringUtils.hasLength(null) = false;
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 日期转字符串(yyyy-MM-dd HH:mm:ss)
     *
     * <pre>
     * StringUtils.dateToString(new Date()) = &quot;2011-06-13 14:00:00&quot;
     * </pre>
     *
     * @param date
     * @return String(yyyy-MM-dd HH:mm:ss)
     */
    public static synchronized String dateToString(Date date) {
        return format.format(date);
    }

    public static synchronized String dateToStringShort(Date date) {
        SimpleDateFormat shortFormat = new SimpleDateFormat("yyyy-MM-dd");
        return shortFormat.format(date);
    }

    /**
     * 格式数字
     *
     * <pre>
     * StringUtils.formatNumber(new Long(100000)) = 100,000.00
     * StringUtils.formatNumber(new Double(100000)) = 100,000.00
     * StringUtils.formatNumber(new Integer(100000)) = 100,000.00
     * StringUtils.formatNumber(new Float(100000)) = 100,000.00
     * StringUtils.formatNumber(new Long(1)) = 1.00
     * StringUtils.formatNumber(new Long(111)) = 111.00
     * StringUtils.formatNumber(new Long(1111)) = 1,111.00
     * </pre>
     *
     * @param num
     *            数字
     * @return 格式化数字字符串
     */
    public static String formatNumber(Number num) {
        String fmtString = null;
        try {
            fmtString = numberFormat.format(num);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        return fmtString;
    }

    /**
     * 根据数字格式,格式化数字
     *
     * <pre>
     * StringUtils.formatNumber(new Long(1000),"##,###.00") = 1,000.00
     * StringUtils.formatNumber(new Long(100000),"#,####.00") = 10,0000.00
     * </pre>
     *
     * @param num
     *            数字
     * @param fmtString
     *            格式串
     * @return
     */
    public static String formatNumber(Number num, String fmtString) {
        DecimalFormat customerFormat = new DecimalFormat(fmtString);
        fmtString = customerFormat.format(num);
        return fmtString;
    }

    /**
     * 数字字符串转数字
     *
     * <pre>
     * StringUtils.parseNumber(&quot;100&quot;) = 100;
     * </pre>
     *
     * @param numberString
     *            数字字符串
     * @return Number
     * @throws ParseException
     */
    public static Number parseNumber(String numberString) {
        Number number = null;
        try {
            number = numberFormat.parse(numberString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(numberString + " is not a number format!");
        }
        return number;
    }

    /**
     * 全角转半角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }


    /**
     * 字符串转int
     *
     * @param input
     * @return
     */
    public static Integer parseInt(String input, Integer defaultVal) {
        try {
            if (!StringUtils.hasText(input)) {
                return defaultVal;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", input)) {
                input = input.replaceAll(",", "");
                return Double.valueOf(input).intValue();
            }
        } catch (NumberFormatException e) {
            LOGGER.error("parse Long error![input=" + input + "] :", e.getMessage());
            return defaultVal;
        }
        return defaultVal;
    }

    /**
     * 处理整型字符串
     *
     * <pre>
     * StringUtils.treatInt("000900") =  "900"
     * StringUtils.treatInt("900") = "900"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String treatInt(String str) {
        int result = 0;
        if (null == str) {
            return null;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                result = result * 10 + str.charAt(i) - 48;
            }
        }
        return String.valueOf(result);
    }

    /**
     * 字符串转long
     *
     * @param input
     * @return
     */
    public static Long parseLong(String input) {
        try {
            if (!StringUtils.hasText(input)) {
                return Long.MIN_VALUE;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", input)) {
                input = input.replaceAll(",", "");
                return Long.valueOf(input);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("parse Long error![input=" + input + "] :", e.getMessage());
            return Long.MIN_VALUE;
        }
        return Long.MIN_VALUE;
    }

    /**
     * 字符串转Long型
     *
     * @param input
     * @param defaultVal
     * @return
     */
    public static Long parseLong(String input, Long defaultVal) {
        try {
            if (!StringUtils.hasText(input)) {
                return defaultVal;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", input)) {
                input = input.replaceAll(",", "");
                return Long.valueOf(input);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("parse Long error![input=" + input + "] :", e.getMessage());
            return defaultVal;
        }
        return defaultVal;
    }

    public static Double parseDouble(String input) {
        try {
            if (!StringUtils.hasText(input)) {
                return Double.MIN_VALUE;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", input)) {
                input = input.replaceAll(",", "");
                return Double.valueOf(input);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("parse Long error![input=" + input + "] :", e.getMessage());
            return Double.MIN_VALUE;
        }
        return Double.MIN_VALUE;
    }

    public static Double parseDouble(String input, Double defaultVal) {
        try {
            if (!StringUtils.hasText(input)) {
                return defaultVal;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", input)) {
                input = input.replaceAll(",", "");
                return Double.valueOf(input);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("parse Long error![input=" + input + "] :", e.getMessage());
            return defaultVal;
        }
        return defaultVal;
    }

    /**
     *
     * 空字符串处理
     *
     * <pre>
     * StringUtils.dealNull(new Integer(2),"0") = 2
     * StringUtils.dealNull(null,"0") = 0
     * </pre>
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static String dealNull(Object str, String defaultValue) {
        return null != str ? str.toString() : defaultValue;
    }

    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     *
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str
     *            要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isNotBlank(String str) {
        if (str != null) {
            int length = str.length();
            if (length == 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    /**
     * 生成订单号 格式：年月日时分秒+随机三位数字
     *
     * @return
     */
    public static String getOrderNo() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())
                + String.valueOf((int) (Math.random() * 900) + 100);
    }

    /**
     * 待特殊字符的unicodeStr转中文
     * ref:http://wx-xw.iteye.com/blog/1508986
     * @param unicodeStr
     * @return
     */
    public static String unicodeEsc2Unicode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }

        return retBuf.toString();
    }

    public static String listToString(List<String> list)
    {
        if(list==null){
            return "";
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;

        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }
}
