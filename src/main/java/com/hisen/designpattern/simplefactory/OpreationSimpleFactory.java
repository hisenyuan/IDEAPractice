package com.hisen.designpattern.simplefactory;

/**
 * 简单工厂模式：传入参数构造相应的对象返回
 * Created by hisen on 17-4-16.
 */
public class OpreationSimpleFactory {

  public static Opreation createOperate(String operate) {
    Opreation opreation = null;
    switch (operate) {
      case "+":
        opreation = new OperationAdd();
        break;
      case "-":
        opreation = new OperationSub();
        break;
      case "*":
        opreation = new OperationMul();
        break;
      case "/":
        opreation = new OperationDiv();
        break;
    }
    return opreation;
  }
}
