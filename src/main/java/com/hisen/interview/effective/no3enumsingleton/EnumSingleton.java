package com.hisen.interview.effective.no3enumsingleton;


/**
 * @author : yhx
 * @date : 2018/1/27 23:16
 * @descriptor : 利用枚举实现单例模式，单例的最佳方法。枚举单例有序列化和线程安全的保证。
 */
public class EnumSingleton {

  private EnumSingleton() {
  }

  public static EnumSingleton getInstance() {
    return Singleton.INSTANCE.getInstance();
  }

  private enum Singleton {
    INSTANCE;
    private EnumSingleton singleton;

    Singleton() {
      singleton = new EnumSingleton();
    }

    public EnumSingleton getInstance() {
      return singleton;
    }
  }
}
