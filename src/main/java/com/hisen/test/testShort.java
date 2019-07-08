package com.hisen.test;

import org.joda.time.DateTime;

/**
 * @Auther: hisenyuan - yuanhaixing@unapy.com
 * @Date: 2018/9/5 17:23
 * @Description:
 */
public class testShort {
    public static void main(String[] args) {
        final DateTime dateTime = new DateTime();
        final int maxValue = Integer.MAX_VALUE;
        final DateTime newTime = dateTime.plusSeconds(maxValue);
        System.out.println(newTime);


        String s = "1";
        int i = 1;
        System.out.println(s.equals(i + ""));
    }
}
