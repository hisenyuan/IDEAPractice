package com.hisen.jars.spring.aop;

/**
 * 横切关注点 - 打印时间
 * Created by hisenyuan on 2017/7/26 at 13:46.
 */
public class TimeHandler {

  /**
   * 打印时间
   */
  public void printTime() {
    System.out.println("Spring AOP >>>>> CurrentTime = " + System.currentTimeMillis());
  }
}
