package com.hisen.interview;

/**
 * @author : yhx
 * @date : 2017/11/10 17:56
 * @descriptor : 多线程下的单例模式
 */
public class MultithreadingSingleton {

    // 保证可见性
    private static volatile MultithreadingSingleton singleton = null;

    private static volatile boolean flag = false;

    // 必须指定为私有方法，否则可以直接new对象
    private MultithreadingSingleton() {
        // 防止被反射；并且保证过程是线程安全的
        synchronized (MultithreadingSingleton.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("禁止反射！");
            }
        }
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
    private static void syncInit() {
        // 减小锁的粒度
        synchronized (MultithreadingSingleton.class) {
            // 二次判断，防止多次创建
            if (singleton == null) {
                singleton = new MultithreadingSingleton();
            }
        }
    }
}
