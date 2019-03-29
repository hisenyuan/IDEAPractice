package com.hisen.jdk.agent.service.impl;

import com.hisen.jdk.agent.service.Fruit;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/29 23:26
 */
public class Orange implements Fruit {
    @Override
    public void show() {
        System.out.println("Orange show method is invoked");
    }
}
