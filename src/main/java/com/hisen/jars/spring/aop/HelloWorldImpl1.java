package com.hisen.jars.spring.aop;

/**
 * Created by hisenyuan on 2017/7/26 at 13:44.
 */
public class HelloWorldImpl1 implements HelloWorld {

  @Override
  public void printHelloWorld() {
    System.out.println("Enter HelloWorldImpl1.printHelloWorld()");
  }

  @Override
  public void doPrint() {
    System.out.println("Enter HelloWorldImpl1.doPrint()");
    return;
  }
}
