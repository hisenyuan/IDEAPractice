package com.hisen.jars.jodaTime;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.junit.Test;

/**
 * Joda-Time 操作常用时间函数 Created by hisenyuan on 2017/7/27 at 17:39.
 */
public class jodaCommonUsage {

    private static final String FORMATE_DATE = "yyyy-MM-dd";
    private static final String FORMATE_FULL = "yyyy-MM-dd HH:mm:ss SSS EE";

    /**
     * 时间计算相关
     */
    @Test
    public void calculateTime() {
        DateTime dt = new DateTime();
        //获取当月第一天
        LocalDate firstDayOfMonth = dt.toLocalDate().withDayOfMonth(1);
        System.out.println("\n获取当月第一天 >>>>> \n" + firstDayOfMonth.toString(FORMATE_DATE));

        //本周第一天和最后一天
        System.out.println("\n本周第一天和最后一天 >>>>> \n" +
                dt.dayOfWeek().withMinimumValue().toString(FORMATE_DATE) + "\n" + dt.dayOfWeek()
                .withMaximumValue().toString(FORMATE_DATE));
        //当月第一天和最后一天
        System.out.println("\n当月第一天和最后一天 >>>>> \n" +
                dt.dayOfMonth().withMinimumValue().toString(FORMATE_DATE) + "\n" + dt.dayOfMonth()
                .withMaximumValue().toString(FORMATE_DATE));
        //当年第一天和最后一天
        System.out.println("\n当年第一天和最后一天 >>>>> \n" +
                dt.dayOfYear().withMinimumValue().toString(FORMATE_DATE) + "\n" + dt.dayOfYear()
                .withMaximumValue().toString(FORMATE_DATE));

        //10天后的时间
        System.out.println("\n10天后的时间 >>>>> \n" + dt.plusDays(10).toString(FORMATE_FULL));

        //10天前的时间
        System.out.println("\n10天前的时间 >>>>> \n" + dt.minusDays(10).toString(FORMATE_FULL));

        //10小时前的时间
        System.out.println("\n10小时前的时间 >>>>> \n" + dt.minusHours(10).toString(FORMATE_FULL));

        //生成特定的时间
        DateTime begin = new DateTime(1992, 4, 3, 00, 00, 00);
        Duration duration = new Duration(begin, dt);
        // 两个时间之间 所差 天，小时 ，分，秒
        System.out.println(
                "两个时间之间的间隔：\n天数：" + duration.getStandardDays() + "\n小时：" + duration
                        .getStandardHours()
                        + "\n分钟："
                        + duration.getStandardMinutes() + "\n秒数：" + duration.getStandardSeconds());
        // 判断是否是闰年 闰月
        System.out.println("\n是否闰月:" + dt.monthOfYear().isLeap());
        System.out.println("是否闰年:" + dt.year().isLeap());
        System.out.println("去年是否闰年:" + dt.minusYears(1).year().isLeap());
        //10 天后 所在周的周一
        System.out.println(
                "\n10 天后 所在周的周一:" + dt.plusDays(10).dayOfWeek().withMinimumValue()
                        .toString(FORMATE_FULL));
    }

    /**
     * 获取当天开始的时间，结束时间
     */
    @Test
    public void testTime() {
        DateTime now = new DateTime();
        String fmr = "yyyyMMddHHmmss";
        String dateTimeStr = now.toString(fmr);
        // 把字符串转为日期
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(fmr);
        DateTime time = DateTime.parse(dateTimeStr,dateTimeFormatter);

        String dayStart = time.withTimeAtStartOfDay().toString(FORMATE_FULL);
        String dayEnd = time.millisOfDay().withMaximumValue().toString(FORMATE_FULL);
        Duration duration = new Duration(time.withTimeAtStartOfDay(), time);
        long i = duration.getStandardSeconds();
        System.out.println("day start:" + dayStart + "\nday  end :" + dayEnd + "\nday  end :" + i);
    }

    @Test
    public void convert2Str() {
        //  yyyy-MM-ddTHH:mm:ssZ
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendYear(4,0)
                .appendLiteral('-')
                .appendMonthOfYear(2)
                .appendLiteral('-')
                .appendDayOfMonth(2)
                .appendLiteral('T')
                .appendHourOfDay(2)
                .appendLiteral(':')
                .appendMinuteOfHour(2)
                .appendLiteral(':')
                .appendSecondOfMinute(2)
                .toFormatter();
        System.out.println(new DateTime().toString(fmt));
    }
}
