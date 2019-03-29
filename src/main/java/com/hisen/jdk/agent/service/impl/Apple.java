package com.hisen.jdk.agent.service.impl;

import com.hisen.jdk.agent.service.Fruit;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/29 23:18
 */
public class Apple implements Fruit {

    @Override
    public void show() {
        System.out.println("Apple show method is invoked");
    }
}
