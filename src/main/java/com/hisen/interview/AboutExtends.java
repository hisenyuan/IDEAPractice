package com.hisen.interview;

/**
 * 变量不能被重写
 *
 * @author hisenyuan 2017年1月18日 下午10:33:33
 */
public class AboutExtends {

  public static void main(String[] args) {
    // 里面的static块方法，new了就会执行
    // new new B()两个都执行，new new A()执行A的
    // static代码块在{}代码块后面执行
    A classA = new B();
    System.out.println(classA.a);
    classA.fun();
    // 输出信息
    // Astatic
    // Bstatic
    // I'm A class
    // I'm B class
    // 1
    // B

    // 多态记忆口诀
    // 变量多态看左边
    // 方法多态看右边
    // 静态多态看左边
  }

  public static class A {

    static {
      System.out.println("Astatic");
    }

    public int a = 0;

    {
      System.out.println("I'm A class");
    }

    public void fun() {
      System.out.println("A");
    }
  }

  public static class B extends A {

    static {
      System.out.println("Bstatic");
    }

    public int a = 1;

    {
      System.out.println("I'm B class");
    }

    public void fun() {
      System.out.println("B");
    }
  }
}
