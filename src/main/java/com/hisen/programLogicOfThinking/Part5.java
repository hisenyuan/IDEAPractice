package com.hisen.programLogicOfThinking;

/**
 * 关于类的基本概念
 * Created by hisen on 17-8-15.
 * E-mail: hisenyuan@gmail.com
 */
public class Part5 {

  /**
   * 与类方法一样，类变量可以直接通过类名访问
   */
  // 类变量
  public static final double PI = 3.14159265358979323846;
  // 类方法
  public static void countArea(double r){
    System.out.println(Part5.PI * r * r);
  }

  /**
   * 实例变量和实例方法
   *
   * 通过对象来访问和操作其内部的数据是一种基本的面向对象思维
   */
  // 实例变量
  private int x;
  private int y;
  // 实例方法，计算直角三角形的斜边长
  public double distance(){
    return Math.sqrt(x*x + y*y);
  }

  public static void main(String[] args) {
    // 调用类方法，在其他类也可以直接通过这种方式调用
    Part5.countArea(2.0);
    // 创建实例/对象
    Part5 p = new Part5();
    // 给实例变量赋值
    p.x = 3;
    p.y = 4;
    // 调用实例方法
    double distance = p.distance();
    System.out.println(distance);
  }


}
