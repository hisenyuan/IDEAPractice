package com.hisen.thread.threadpool;

import java.util.LinkedList;
import java.util.List;

/**
 * 线程池类
 * 参考：http://blog.csdn.net/hsuxu/article/details/8985931
 * Created by hisen on 17-8-12.
 */
public final class ThreadPool {

  // 线程池中默认线程个数
  private static int worker_num = 5;
  // 工作线程
  private WorkerThread[] workerThreads;
  // 未处理的任务
  private static volatile int finished_task = 0;
  // 任务队列，作为一个缓冲,LinkedList线程安全 List线程不安全
  List<Runnable> taskQueue = new LinkedList<Runnable>();
  // 自身实例
  private static ThreadPool threadPool;

  // 默认启动5个
  private ThreadPool() {
    this(5);
  }

  // 创建线程池,worker_num为线程池中工作线程的个数
  private ThreadPool(int worker_num) {
    ThreadPool.worker_num = worker_num;
    workerThreads = new WorkerThread[worker_num];
    for (int i = 0; i < worker_num; i++) {
      workerThreads[i] = new WorkerThread();
      // 开启线程池中的线程
      workerThreads[i].start();
    }
  }

  // 获得默认线程个数的线程池
  public static ThreadPool getThreadPool() {
    return getThreadPool(ThreadPool.worker_num);
  }

  // 获得一个指定工作线程数的线程池
  public static ThreadPool getThreadPool(int worker_num_1) {
    if (worker_num <= 0) {
      worker_num = ThreadPool.worker_num;
    }
    if (threadPool == null) {
      threadPool = new ThreadPool(worker_num_1);
    }
    return threadPool;
  }

  // 执行任务（把任务加入任务队列，什么时候执行由线程池管理）
  public void execute(Runnable task) {
    synchronized (taskQueue) {
      taskQueue.add(task);
      taskQueue.notify();
    }
  }

  // 执行任务（把任务加入任务队列，什么时候执行由线程池管理）
  public void execute(Runnable[] task) {
    synchronized (taskQueue) {
      for (Runnable r : task) {
        taskQueue.add(r);
      }
      taskQueue.notify();
    }
  }

  // 批量执行任务,其实只是把任务加入任务队列，什么时候执行由线程池管理器决定
  public void execute(List<Runnable> task) {
    synchronized (taskQueue) {
      for (Runnable t : task) {
        taskQueue.add(t);
      }
      taskQueue.notify();
    }
  }

  public void destory() {
    while (!taskQueue.isEmpty()) {
      try {
        // 如果任务还没有执行，先睡一会
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    for (int i = 0; i < worker_num; i++) {
      workerThreads[i].stopWorker();
      workerThreads[i] = null;
    }
    // 释放，防止OOM
    threadPool = null;
    taskQueue.clear();
  }

  /**
   * 内部类，工作线程
   */
  private class WorkerThread extends Thread {

    // 判断线程是否有效，用于结束线程
    private boolean isRunning = true;

    /**
     * 关键，如果任务队列不为空则取出执行，若为空则等待
     */
    public void run() {
      Runnable r = null;
      //isRunning 为false则线程无效
      while (isRunning) {
        synchronized (taskQueue) {
          while (taskQueue.isEmpty()) {
            try {
              // 队列为空休息会
              taskQueue.wait(20);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          if (!taskQueue.isEmpty())
          // 不为空取出任务
          {
            r = taskQueue.remove(0);
          }
        }
        if (r != null)
        // 执行任务
        {
          r.run();
        }
        ++finished_task;
        r = null;
      }
    }

    // 停止工作，让该线程自然执行完run方法，自然结束
    public void stopWorker() {
      isRunning = false;
    }
  }

  // 返回工作线程的个数
  public int getWorkThreadNumber() {
    return worker_num;
  }

  // 返回已完成任务的个数,这里的已完成是只出了任务队列的任务个数，可能该任务并没有实际执行完成
  public int getFinishedTasknumber() {
    return finished_task;
  }

  // 返回等待的任务个数
  public int getWaitTasknumber() {
    return taskQueue.size();
  }

  @Override
  public String toString() {
    return "WorkThread number:" + worker_num + "  finished task number:"
        + finished_task + "  wait task number:" + getWaitTasknumber();
  }
}
