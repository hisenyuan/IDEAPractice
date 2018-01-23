package com.hisen.thread.count_click_by_redis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    /**
     * 5 - corePoolSize：核心池的大小
     * 10 - maximumPoolSize：线程池最大线程数，它表示在线程池中最多能创建多少个线程
     * 200 - keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。
     * unit - unit：参数keepAliveTime的时间单位，有7种取值
     * workQueue：一个阻塞队列，用来存储等待执行的任务
     */
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        50,
        100,
        200,
        TimeUnit.MICROSECONDS,
        new ArrayBlockingQueue<Runnable>(50));
    // 开启50个线程
    for (int i = 0; i < 50; i++) {
      executor.execute(new CountClickByRedisThread(i));
    }
    System.out.println("已经开启所有的子线程");
    // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
    executor.shutdown();
    // 判断所有线程是否已经执行完毕
    while (true) {
      if (executor.isTerminated()) {
        System.out.println("所有的子线程都结束了！");
        // 清除redis数据
        ClickRedis.declare();
        break;
      }
      Thread.sleep(100);
    }
  }

}
