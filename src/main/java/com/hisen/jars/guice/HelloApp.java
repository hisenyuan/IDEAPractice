package com.hisen.jars.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.hisen.jars.guice.service.BaseServer;
import com.hisen.jars.guice.service.HelloService;
import com.hisen.jars.guice.service.impl.HelloServiceImpl;
import org.junit.Test;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/4/24 16:13
 */
public class HelloApp extends BaseServer {
    @Inject
    private HelloServiceImpl hello;

    @Test
    public void testSayHello() {
        // 方式一
        Injector injector = Guice.createInjector();
        HelloService helloService = injector.getInstance(HelloService.class);
        helloService.sayHello("hisen");

        // 方式二 其实是在BaseServer中做了方式1的事情 【类似Spring的方式】
        hello.sayHello("1");
    }
}
