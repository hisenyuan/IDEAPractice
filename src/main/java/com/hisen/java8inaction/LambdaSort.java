package com.hisen.java8inaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hisenyuan on 2017/8/3 at 11:40.
 */
public class LambdaSort {

  public static void main(String[] args) {
    List<String> list = Arrays.asList("hisen", "Get_GCD_LCM", "work", "name", "apple");
    //利用lambda语法
    Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
    for (String s : list) {
      System.out.println(s);
    }
    System.out.println("--------");
    //利用匿名内部类
    Collections.sort(list, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o2.compareTo(o1);//反过来之后首字母倒序
      }
    });
    for (String s : list) {
      System.out.println(s);
    }

    System.out.println("--------");
    //转换大小写
    List<String> collect = list.stream().map((String name) -> {
      return name.toUpperCase();
    }).collect(Collectors.toList());
    for (String s : collect){
      System.out.println("转换为大写 >>>>> "+s);
    }
  }
}
