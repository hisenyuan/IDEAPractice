package com.hisen.interview.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hisen on 17-8-6.
 */
public class LambdaExpression {

  public static void main(String[] args) {
    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
    //java8之前的写法
    Collections.sort(names, new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        return a.compareTo(b);
      }
    });
    System.out.println("传统排序："+names);//[anna, mike, peter, xenia]
    //常规的Lambda表达式
    Collections.sort(names,(String a,String b) ->{
      return a.compareTo(b);
    });
    System.out.println("常规Lambda："+names);//[anna, mike, peter, xenia]
    //缩短
    Collections.sort(names,(String a,String b) -> b.compareTo(a));

    //再缩短,编译器自动根据上下文来决定参数的类型
    Collections.sort(names,(a,b) -> b.compareTo(a));
    System.out.println("缩短的lambda："+names);//[xenia, peter, mike, anna]

  }
}
