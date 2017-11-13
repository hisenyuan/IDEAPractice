package com.hisen.interview.tiger20171110.multithreadingwrite;

/**
 * @author : yhx
 * @date : 2017/11/13 16:47
 * @descriptor : 多线程同时写入文件
 */
public class Write2FileMain {

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.println("第" + i + "个线程正在写");
      new MultithreadingWrite().start();
    }
  }
}
