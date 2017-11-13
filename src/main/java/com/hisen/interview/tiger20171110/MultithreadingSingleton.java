package com.hisen.interview.tiger20171110;

/**
 * @author : yhx
 * @date : 2017/11/10 17:56
 * @descriptor : 多线程下的单例模式
 */
public class MultithreadingSingleton {

  private static MultithreadingSingleton singleton = null;

  public MultithreadingSingleton() {
    System.out.println("被创建了");
  }

  // 获取实例
  public static MultithreadingSingleton getInstance() {
    if (singleton == null) {
      syncInit();
    }
    return singleton;
  }

  /**
   * 加入同步方法
   */
  private static synchronized void syncInit() {
    if (singleton == null) {
      singleton = new MultithreadingSingleton();
    }
  }
}
