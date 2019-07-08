package com.hisen.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2018/10/11 12:18
 */
public class GeometricProgression {
    public static void main(String[] args) {
        int a1 = 5;
        int n = 9;
        List<Integer> aNs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            aNs.add((int) Math.pow(a1,i));
        }
        System.out.println(JSON.toJSONString(aNs));


//        retries:最大重试次数，默认10
//        factor:指数因子使用，默认2
//        minTimeout: 第一次重试前等待时间，默认1000ms
//        maxTimeout: 间隔两次重试的等待时间，默认Infinity
//        randomize: 随机化超时时间，默认false

        int retries = 10;
        int factor = 2;
        int minTimeout = 5;
        int maxTimeout = 10;
    }
}
