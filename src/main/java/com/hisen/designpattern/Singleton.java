package com.hisen.designpattern;

/**
 * @author : yhx
 * @date : 2017/11/9 21:18
 * @descriptor : 单例模式
 */
public class Singleton {

  private static Singleton instance;

  /**
   * 懒汉模式
   */
  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }

  //将构造器使用private修饰，隐藏该构造器
  private Singleton() {
    System.out.println("Singleton被构造！");
  }

  public static void main(String[] args) {
    // 获取两个实例，最终输入的结果为 true
    Singleton singleton1 = Singleton.getInstance();
    Singleton singleton2 = Singleton.getInstance();
    // 比较两个对象是否相等
    System.out.println(singleton1==singleton2);
  }
}
