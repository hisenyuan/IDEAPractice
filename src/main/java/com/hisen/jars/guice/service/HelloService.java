package com.hisen.jars.guice.service;

import com.google.inject.ImplementedBy;
import com.hisen.jars.guice.service.impl.HelloServiceImpl;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/4/24 16:12
 */
@ImplementedBy(HelloServiceImpl.class)
public interface HelloService {
    void sayHello(String name);
}
