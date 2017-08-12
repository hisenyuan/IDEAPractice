package com.hisen.thread.threadpool;

/**
 * Created by hisen on 17-8-12.
 */
public class TestThreadPool {

  public static void main(String[] args) {
    //创建一个容量为3的线程池
    ThreadPool threadPool = ThreadPool.getThreadPool(3);
    //添加线程
    threadPool.execute(new Runnable[]{new Task(),new Task(),new Task()});
    threadPool.execute(new Runnable[]{new Task(),new Task()});
    threadPool.execute(new Runnable[]{new Task()});
    System.out.println(threadPool);
    threadPool.destory();
    System.out.println(threadPool);

//    WorkThread number:3  finished task number:0  wait task number:6
//    任务 1 完成
//    任务 2 完成
//    任务 3 完成
//    任务 4 完成
//    任务 6 完成
//    任务 5 完成
//    WorkThread number:3  finished task number:6  wait task number:0
  }

  // 任务类
  static class Task implements Runnable {
    private static volatile int i = 1;
    @Override
    // 执行任务
    public void run() {
      // 自定义任务内容
      System.out.println("任务 " + (i++) + " 完成");
    }
  }
}
