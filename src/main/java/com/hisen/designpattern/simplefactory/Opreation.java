package com.hisen.designpattern.simplefactory;

/**
 * 业务类：计算操作父类
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
