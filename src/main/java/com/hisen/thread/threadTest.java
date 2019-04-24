package com.hisen.thread;

// 创建一个新的线程
class NewThread implements Runnable {

  Thread t;

  NewThread() {
    // 创建第二个新线程
    t = new Thread(this, "Demo Thread");
    System.out.println("Child thread: " + t);
    t.start(); // 开始线程
  }

  // 第二个线程入口
  public void run() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Child Thread: " + i);
        // 暂停线程
        Thread.sleep(50);
      }
    } catch (InterruptedException e) {
      System.out.println("Child interrupted.");
    }
    System.out.println("Exiting child thread.");
  }
}

public class threadTest {

  public static void main(String args[]) {
    new NewThread(); // 创建一个新线程
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("CrcUtils Thread: " + i);
        Thread.sleep(100);
      }
    } catch (InterruptedException e) {
      System.out.println("CrcUtils thread interrupted.");
    }
    System.out.println("CrcUtils thread exiting.");
  }
}
