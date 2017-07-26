package com.hisen.jars.spring.aop;

/**
 * 横切关注点 - 打印日志
 * Created by hisenyuan on 2017/7/26 at 14:12.
 */
public class LogHandler {

  public void LogBefore() {
    System.out.println("Spring AOP >>>>> Log before method");
  }

  public void LogAfter() {
    System.out.println("Spring AOP >>>>> Log after method");
  }
}
