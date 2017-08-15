package com.hisen.lambda;

import java.util.function.Consumer;

/**
 * Lambda可以访问
 * LambdaScopeTest的成员变量、
 * FirstLevel的成员变量、
 * methodInFirstLevel的参数变量。
 *
 * Created by hisenyuan on 2017/8/3 at 10:52.
 */
public class LambdaScopeTest {
  public int x = 0;

  class FisrtLevel{
    public int x = 1;
    void methodInFirstLevel(int x){
      Consumer<Integer> myConsumer = (y)->{
        System.out.println("x = " + x);
        System.out.println("y = " + y);
//        在lambda中，this不是指向lambda表达式产生的那个SAM对象，而是声明它的外部对象。
        System.out.println("this.x = " + this.x);
        System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);
      };
      myConsumer.accept(9);
    }
  }

  public static void main(String[] args) {
    LambdaScopeTest scopeTest = new LambdaScopeTest();
    LambdaScopeTest.FisrtLevel f = scopeTest.new FisrtLevel();
    f.methodInFirstLevel(23);
//    执行结果
//    x = 23
//    y = 9
//    this.x = 1
//    LambdaScopeTest.this.x = 0
  }
}
