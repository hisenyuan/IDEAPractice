package com.hisen.lambda;

/**
 * Created by hisenyuan on 2017/8/3 at 10:29.
 */
public class Calculator {

  interface integerMath {

    int operation(int a, int b);
  }

  public int operateBinary(int a, int b, integerMath op) {
    return op.operation(a, b);
  }

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    /**
     * addition和subtraction就可以看做是IntegerMath省略了operation名称的方法。
     */
    integerMath addition = ((a, b) -> a + b);
    integerMath subtraction = (a, b) -> a - b;
    System.out.println("40 + 20 = " + calculator.operateBinary(40, 20, addition));
    System.out.println("40 - 20 = " + calculator.operateBinary(40, 20, subtraction));
//    40 + 20 = 60
//    40 - 20 = 20
  }
}
