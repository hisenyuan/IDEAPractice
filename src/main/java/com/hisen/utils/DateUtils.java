package com.hisen.utils;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author hisenyuan
 * @time 2018/3/28 9:58
 * @description Date与字符串之间的转换
 */
public class DateUtils {

    /**
     * 将字符串转换为日期
     * @param strTime 字符串的时间
     * @param formatStr 字符串格式
     * @return date时间
     */
    public static Date str2Date(String strTime,String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(strTime);
        DateTime dateTime = dateTimeFormatter.parseDateTime(formatStr);
        return dateTime.toDate();
    }

    /**
     * 将日期转换为字符串
     * @param dateTime 日期
     * @param formatStr 格式
     * @return 返回时间字符串
     */
    public static String date2str(Date dateTime,String formatStr){
        DateTime time = new DateTime(dateTime.getTime());
        return time.toString(formatStr);
    }

    /**
     * 获得当前时间的字符串格式
     * @param formatStr 想要的日期格式
     * @return
     */
    public static String getFormatTimeStr(String formatStr){
        DateTime time = new DateTime();
        return time.toString(formatStr);
    }
}
