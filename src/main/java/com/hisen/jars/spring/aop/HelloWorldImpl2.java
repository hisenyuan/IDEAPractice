package com.hisen.jars.spring.aop;

/**
 * Created by hisenyuan on 2017/7/26 at 13:45.
 */
public class HelloWorldImpl2 implements HelloWorld {

  @Override
  public void printHelloWorld() {
    System.out.println("Enter HelloWorldImpl2.printHelloWorld()");
  }

  @Override
  public void doPrint() {
    System.out.println("Enter HelloWorldImpl2.doPrint()");
    return;
  }
}
