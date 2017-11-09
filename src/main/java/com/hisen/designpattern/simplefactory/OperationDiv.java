package com.hisen.designpattern.simplefactory;

/**
 * 业务类：除法
 * Created by hisen on 17-4-16.
 */
public class OperationDiv extends Opreation {

  @Override
  public double getResult() {
    double result = 0;
    if (getNumberB() == 0) {
      try {
        throw new Exception("除数不能为0");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    result = getNumberA() / getNumberB();
    return result;
  }
}
