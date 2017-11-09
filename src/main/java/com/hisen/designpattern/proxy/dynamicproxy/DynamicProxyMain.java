package com.hisen.designpattern.proxy.dynamicproxy;

import com.hisen.designpattern.proxy.staticproxy.BuyCar;
import com.hisen.designpattern.proxy.staticproxy.Customser;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author : yhx
 * @date : 2017/11/9 22:03
 * @descriptor : 动态代理测试类
 */
public class DynamicProxyMain {

  /**
   * 动态代理好处
   * 使用Java动态代理机制的好处：
   * 1、减少编程的工作量：假如需要实现多种代理处理逻辑，
   *    只要写多个代理处理器就可以了，无需每种方式都写一个代理类。
   * 2、系统扩展性和维护性增强，程序修改起来也方便多了(一般只要改代理处理器类就行了)。
   * @param args
   */
  public static void main(String[] args) {
    // 要代理的真实对象
    Customser customser = new Customser();
    customser.setCash(100000);
    // 我们要代理那个对象，就把这个对象传进去，最后是通过该对象来调用其他方法
    InvocationHandler handler = new DynamicProxy(customser);
    // 简单的模拟，于是这里顾客是直接买车的，没有通过4S店
    BuyCar buyCarHandler = (BuyCar) Proxy.newProxyInstance(handler.getClass().getClassLoader(),customser.getClass().getInterfaces(),handler);
    buyCarHandler.buyCar();
  }
}
