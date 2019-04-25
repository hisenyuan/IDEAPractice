package com.hisen.jars.guice.service.impl;


import com.google.inject.Singleton;
import com.hisen.jars.guice.service.HelloService;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/4/24 16:13
 */
@Singleton
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
