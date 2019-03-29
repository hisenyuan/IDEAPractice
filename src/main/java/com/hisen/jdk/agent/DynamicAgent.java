package com.hisen.jdk.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author hisenyuan
 * @Description $end$
 * @Date 2019/3/29 23:17
 */
public class DynamicAgent {
    // implement InvocationHandler interface，init oriObject
    static class MyHandler implements InvocationHandler {
        private Object proxy;

        MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        // customer invoke
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("-> before invoking");
            // really invoke
            Object ret = method.invoke(this.proxy, args);
            System.out.println("-> after invoking");
            return ret;
        }
    }

    // return a modify object
    static Object agent(Class interfaceClazz, Object proxy) {
        final Class[] classes = {interfaceClazz};
        final MyHandler myHandler = new MyHandler(proxy);
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), classes, myHandler);
    }
}
