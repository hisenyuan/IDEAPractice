package com.hisen.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : yhx
 * @date : 2017/11/16 22:01
 * @descriptor : 线程池的使用
 */
public class AboutThreadPoolExecutor {

  public static void main(String[] args) {
    /**
     * 5 - corePoolSize：核心池的大小
     * 10 - maximumPoolSize：线程池最大线程数，它表示在线程池中最多能创建多少个线程
     * 200 - keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。
     * unit - unit：参数keepAliveTime的时间单位，有7种取值
     * workQueue：一个阻塞队列，用来存储等待执行的任务
     */
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
        TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(5));
    for (int i = 0; i < 15; i++) {
      MyTask myTask = new MyTask(i);
      executor.execute(myTask);
      System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
          executor.getQueue().size()+"，已执行完成的任务数目："+executor.getCompletedTaskCount());
    }
  }
  static class MyTask implements Runnable{
    private int taskNum;

    public MyTask(int taskNum) {
      this.taskNum = taskNum;
    }

    @Override
    public void run() {
      System.out.println("正在执行Task " + taskNum);
      try {
        Thread.sleep(4000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("task " + taskNum + "执行完毕");
    }
  }
}
