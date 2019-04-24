package com.hisen.interview;

import java.util.Arrays;

/**
 * @Author hisenyuan
 * @Description 在一段字符串中找出第一个只出现一次的字符，没有返回空串
 * @Date 2019/4/2 14:50
 */
public class FindFirstNotDupStr {
    public static void main(String[] args) {
        String str = "ADBABCAABB";

        final int[] indexs = getDupCountArr(str);

        final String c = firstFirstNotDup(str, indexs);

        System.out.println(c);

        System.out.println(Arrays.toString(indexs));
    }

    // 找出第一个只出现一次的字符
    private static String firstFirstNotDup(String str, int[] indexs) {
        for (int i = 0; i < str.length(); i++) {
            int at = str.charAt(i);
            final int id = (at - 'A');
            if (indexs[id] == 1) {
                return String.valueOf((char) at);
            }
        }
        return "";
    }

    // 利用字符ascii码作为数组下标，记录字符出现次数
    private static int[] getDupCountArr(String str) {
        final int[] indexs = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int at = str.charAt(i);
            final int id = (at - 'A');
            indexs[id] = indexs[id] + 1;
        }
        return indexs;
    }
}
