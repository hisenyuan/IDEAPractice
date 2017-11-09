package com.hisen.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : yhx
 * @date : 2017/11/9 21:58
 * @descriptor : 动态代理 - 利用反射原理
 */
public class DynamicProxy implements InvocationHandler {
  // 被代理的对象
  Object object;

  // 将被代理的类传给动态代理类构造函数中
  public DynamicProxy(Object object) {
    this.object = object;
  }

  /**
   * 覆盖InvocationHandler接口中的invoke()方法
   * 在不改变原有代码的情况下进行扩展
   * 下面的before、after就是我们可以进行特殊代码切入的扩展点了。
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // before
    Object result = method.invoke(this.object, args);
    // after
    return result;
  }
}
