package com.example.entry.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/25 09:59
 * @Description:
 * @Version 1.0
 */
public class DateUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String HourMin_FORMAT = "HH:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 字符串按格式转换为日期
     *
     * @param sdate
     *          源字符串
     * @param format
     *          转换的格式
     * @return
     */
    public static Date toDate(final String sdate, final String format) {
        if (sdate == null || sdate.trim().equals("")) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date ret = null;
        try {
            ret = (Date) df.parseObject(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private static final DateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static Date toLongDateTime2(final String date) {
        return toDate(date, DATE_FORMAT);
    }

    public static Date toLongDateTimeTwo(final String date) {
        return toDate(date, DATE_TIME_FORMAT);
    }

    public static Date toLongDateTime(final String date) {
        return toDate(date, DATE_TIME_FORMAT_2);
    }

    public static String toCNDateTime(Date date) {
        return getFormat(DATE_TIME_FORMAT_2).format(date);
    }

    public static String toDateTimeDetail(Date date) {
        return getFormat(YYYYMMDDHHMMSS).format(date);
    }

    public static int getDate(Date date) {
        if (date == null) {
            return 0;
        } else {
            DateFormat df = new SimpleDateFormat(YYYYMMDD);
            return Integer.parseInt(df.format(date));
        }
    }

    public static String format(Date date, String dateFormat) {
        if (date == null) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat(dateFormat);
            return df.format(date);
        }
    }

    public static int date2Seconds(Date date) {
        if (null == date) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long millis = cal.getTimeInMillis();
        int seconds = (new Long(millis / 1000)).intValue();// 毫秒转换成秒
        return seconds;
    }

    /**
     * 在日期上添加多几天并返回日期.
     * @param date
     * @param n
     * @return
     */
    public static Date addDay(Date date, int n) {
        try {
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            cd.add(Calendar.DATE, n);
            return cd.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseSec2Date(Integer time) {
        if (null == time) {
            return new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time.longValue() * 1000);
        Date date = cal.getTime();
        return date;
    }

    public static String getInterval(Date startTime, Date endTime) {
        String interval = null;
        long time = endTime.getTime() - startTime.getTime();// 得出的时间间隔是毫秒
        if (time / 1000 < 10 && time / 1000 >= 0) {
            // 如果时间间隔小于10秒则显示“刚刚�?time/10得出的时间间隔的单位是秒
            interval = time / 1000 + "秒";
        } else if (time / 3600000 < 24 && time / 3600000 >= 0) {
            // 如果时间间隔小于24小时则显示多少小时前
            int h = (int) (time / 3600000);// 得出的时间间隔的单位是小时
            interval = h + "小时";
        } else if (time / 60000 < 60 && time / 60000 > 0) {
            // 如果时间间隔小于60分钟则显示多少分钟前
            int m = (int) ((time % 3600000) / 60000);// 得出的时间间隔的单位是分钟
            interval = m + "分钟";
        } else if (time / 1000 < 60) {
            int se = (int) ((time % 60000) / 1000);
            interval = se + "秒";

        }
        return interval;
    }

    public static int getIntervalYear(Date startDate, Date endDate) {
        int startYear = 0;
        int endYear = 0;

        if (null != startDate) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(startDate);
            startYear = cal1.get(Calendar.YEAR);
        }
        if (null != endDate) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(endDate);
            endYear = cal2.get(Calendar.YEAR);
        }

        return endYear - startYear;
    }

    public static String getDiffTimeStr(Date start, Date end) {
        String time = "";
        if (start != null && end != null) {
            int t = (int) (end.getTime() - start.getTime()) / 1000;
            String h = "";
            String m = "";
            String s = "";
            h = (int) t / 3600 + "";
            m = (int) (t % 3600) / 60 + "";
            s = t % 60 + "";
            if (h.length() <= 1) {
                h = "0" + h;
            }
            if (m.length() <= 1) {
                m = "0" + m;
            }
            if (s.length() <= 1) {
                s = "0" + s;
            }
            time = h + ":" + m + ":" + s;
        }
        return time;
    }

    public static Boolean verifySameDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        if (format(d1, "yyyy-MM-dd").equals(format(d2, "yyyy-MM-dd")))
            return true;
        return false;
    }

    public static Boolean verifySameWeek(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        int week1 = cal1.get(Calendar.WEEK_OF_YEAR);
        int week2 = cal2.get(Calendar.WEEK_OF_YEAR);
        if (week1 == week2)
            return true;
        else
            return false;
    }

    public static Boolean verifySameMonth(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        if (format(d1, "yyyy-MM").equals(format(d2, "yyyy-MM")))
            return true;
        return false;
    }

    public static long date2Long(Date date, String format) {
        if (date == null)
            return 0;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        return new Long(dateStr);
    }

    /**
     * 判断两个时间是否在同一天之内
     *
     * @param inputTime
     * @param currentTime
     * @return
     */
    public static boolean isTheSameDay(long inputTime, long currentTime) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTimeInMillis(inputTime * 1000);
        c2.setTimeInMillis(currentTime * 1000);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && (c1.get(Calendar.DAY_OF_MONTH) == c2
                .get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获得本月最后一天
     */
    public static Date getLastDayOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);// 设为当前月的1号
        cal.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        cal.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        return cal.getTime();
    }

    /**
     * 获得本月第一天
     */
    public static Date getFirstDayOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);// 设为当前月的1号
        return cal.getTime();
    }

    /**
     * 在日期上添加多几分钟并返回日期.
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    public static Date getDate(int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);

        return cal.getTime();
    }

    /**
     * 获取当天时间的两个临界点
     * @return
     */
    public static List<Date> getCurrentDays() {
        List<Date> list = new ArrayList<Date>(2);

        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        list.add(todayStart.getTime());

        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        list.add(todayEnd.getTime());
        return list;
    }

    public static List<Date> getYesTodayDays(){
        List<Date> list = new ArrayList<Date>();
        Calendar yesterdayStart = Calendar.getInstance();
        yesterdayStart.add(Calendar.DATE, -1);

        yesterdayStart.set(Calendar.HOUR_OF_DAY, 0);
        yesterdayStart.set(Calendar.MINUTE, 0);
        yesterdayStart.set(Calendar.SECOND, 0);
        yesterdayStart.set(Calendar.MILLISECOND, 0);
        list.add(yesterdayStart.getTime());

        Calendar yesterdayEnd = Calendar.getInstance();
        yesterdayEnd.add(Calendar.DATE, -1);
        yesterdayEnd.set(Calendar.HOUR_OF_DAY, 23);
        yesterdayEnd.set(Calendar.MINUTE, 59);
        yesterdayEnd.set(Calendar.SECOND, 59);
        yesterdayEnd.set(Calendar.MILLISECOND, 999);
        list.add(yesterdayEnd.getTime());
        return list;
    }

    /**
     * 获得相差天数
     * @param bigDate
     * @param smallDate
     * @return
     */
    public static int getIntervalDay(Date bigDate,Date smallDate){
        long time=bigDate.getTime()-smallDate.getTime();
        int day=(int)(time/(1000*3600*24));
        return day;
    }

    /**
     * 获取今年
     * @return
     */
    public static int getYear()
    {
        return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
    }

    /**
     * 比较两个时间大小
     * @param str
     * @return str 大为true
     */
    public static boolean compareDate(String str,String str1)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        try {
            Date date = sdf.parse(str);
            Date date1 = sdf.parse(str1);
            if (date.getTime() > date1.getTime()) {
                return true;
            } else if (date.getTime() < date1.getTime()) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
