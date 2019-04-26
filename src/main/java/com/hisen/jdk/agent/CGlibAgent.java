package com.hisen.jdk.agent;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/29 23:37
 */
public class CGlibAgent implements MethodInterceptor {

    public Object getInstance(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("-> before invoking");
        //真正调用
        Object ret = methodProxy.invokeSuper(o, objects);
        System.out.println("-> after invoking");
        return ret;
    }
}
