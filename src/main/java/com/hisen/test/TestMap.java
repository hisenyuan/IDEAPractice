package com.hisen.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hisen.interview.math.RandomCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/2/25 16:20
 */
public class TestMap {
    public static void main(String[] args) {
        Map test = new HashMap();
        test.put("1", "身份证");
        test.put("4", "回乡证");
        test.put("5", "台胞证");
        test.put("9", "港澳居民居住证");
        test.put("10", "台湾居民居住证");
        test.put("3", "外籍护照");
        String s = JSON.toJSONString(test);
        System.out.println(s);
//        <entry key="1" value="身份证"></entry>
//                <entry key="4" value="回乡证"></entry>
//                <entry key="5" value="台胞证"></entry>
//                <!--   新增证件类型 袁海幸 2019-05-30 start-->
//                <entry key="9" value="港澳居民居住证"></entry>
//                <entry key="10" value="台湾居民居住证"></entry>
//                <!--   新增证件类型 袁海幸 2019-05-30 end-->
//                <entry key="3" value="外籍护照"></entry>
//        for (int i = 0; i < 1000; i++) {
//            String str = "hisen yuan$%^" + code(5) + " test " + code(15);
//            final String s = str.replaceAll("[^a-zA-Z\\\\s]", "");
//            System.out.println("bef: " + str + "\naft：" + s +"\n");
//        }
    }

    public static String code(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            //利用22~126的随机数，把ascii码转成字符拼接
            int random = (int) (Math.random() * 126);
            builder.append((char) Integer.parseInt(String.valueOf(random)));
        }
        return builder.toString();
    }
}
