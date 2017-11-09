package com.hisen.designpattern.proxy.staticproxy;

/**
 * @author : yhx
 * @date : 2017/11/9 21:48
 * @descriptor : 静态代理模式测试类 - 顾客通过4S店买车
 */
public class BuyCarMain {

  /**
   * 通过代理模式可以方便的对一些现有方法进行扩展
   * @param args
   */
  public static void main(String[] args) {
    Customser customser = new Customser();
    customser.setCash(10000);
    BuyCarProxy buyCarProxy = new BuyCarProxy(customser);
    buyCarProxy.buyCar();
  }
}
