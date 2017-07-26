package com.hisen.jars.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Spring AOP
 * 在不倾入具体代码的情况下，增加方法执行前后的一些记录
 * Created by hisenyuan on 2017/7/26 at 13:49.
 */
public class MainTest {

  public static void main(String[] args) {
    //获取上下文
    ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "classpath:spring/aop/spring-aop.xml");
    //获取bean
    HelloWorld hw1 = (HelloWorld) ctx.getBean("helloWorldImpl1");
    HelloWorld hw2 = (HelloWorld) ctx.getBean("helloWorldImpl2");
    //执行方法
    hw1.printHelloWorld();
    System.out.println("-------------------");
    hw1.doPrint();

    System.out.println("-------------------");

    //执行方法
    hw2.printHelloWorld();
    System.out.println("-------------------");
    hw2.doPrint();

//    输出结果如下 （省略了部分不需要的信息）
//    Spring AOP >>>>> Log before method
//    Spring AOP >>>>> CurrentTime = 1501049833535
//    Enter HelloWorldImpl1.printHelloWorld()
//    Spring AOP >>>>> CurrentTime = 1501049833536
//    Spring AOP >>>>> Log after method
//    -------------------
//    Spring AOP >>>>> Log before method
//    Spring AOP >>>>> CurrentTime = 1501049833537
//    Enter HelloWorldImpl1.doPrint()
//    Spring AOP >>>>> CurrentTime = 1501049833537
//    Spring AOP >>>>> Log after method
//    -------------------
//    Spring AOP >>>>> Log before method
//    Spring AOP >>>>> CurrentTime = 1501049833538
//    Enter HelloWorldImpl2.printHelloWorld()
//    Spring AOP >>>>> CurrentTime = 1501049833538
//    Spring AOP >>>>> Log after method
//    -------------------
//    Spring AOP >>>>> Log before method
//    Spring AOP >>>>> CurrentTime = 1501049833541
//    Enter HelloWorldImpl2.doPrint()
//    Spring AOP >>>>> CurrentTime = 1501049833541
//    Spring AOP >>>>> Log after method
  }
}
