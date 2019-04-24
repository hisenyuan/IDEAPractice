package com.hisen.jars.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hisen.jars.guice.service.HelloService;
import org.junit.Test;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/4/24 16:13
 */
public class HelloApp {

    @Test
    public void testSayHello() {
        Injector injector = Guice.createInjector();
        HelloService helloService = injector.getInstance(HelloService.class);
        helloService.sayHello("hisen");
    }
}
