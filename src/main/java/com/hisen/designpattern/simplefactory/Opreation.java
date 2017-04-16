package com.hisen.designpattern.simplefactory;

/**
 * 业务类：计算操作父类
 * 继承的子类要重写父类方法有几个条件：
 * 1.父类中的方法在子类中必须可见
 * 2.子类和父类的方法必须是实例方法，如果父类是static方法而子类是实例方法，或者相反都会报错
 * 3.子类和父类的方法必须要具有相同的函数名称、参数列表，并且子类的返回值与父类相同或者是父类返回类型的子类型
 * 4.子类方法的访问权限不能小于父类方法的访问权限
 * 5.子类方法不能比父类方法抛出更多的编译时异常
 * Created by hisen on 17-4-16.
 */
public class Opreation {

  private double numberA = 0;
  private double numberB = 0;

  public double getNumberA() {
    return numberA;
  }

  public void setNumberA(double numberA) {
    this.numberA = numberA;
  }

  public double getNumberB() {
    return numberB;
  }

  public void setNumberB(double numberB) {
    this.numberB = numberB;
  }

  public double getResult() {
    double result = 0;
    return result;
  }
}
