package com.hisen.split;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 特殊字符串转arraylist 利用正则表达式
 *
 * @author hisenyuan
 */
public class Str2List {

  public static void main(String[] args) {
    function1();
    System.out.println("=====分割线=====");
    function2();

  }

  /*
   * 利用正则表达式
   */
  public static void function1() {
    String st = "[\"2.2.2.2\",\"1.1.1.1\",\"6.6.6.6\",\"4.4.4.4\"]";
    String patternString = "(\\d\\.\\d\\.\\d\\.\\d)";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(st);
    ArrayList<String> list = new ArrayList<>();
    while (matcher.find()) {
      list.add(matcher.group(1));
    }
    for (String item : list) {
      System.out.println("" + item);
    }

  }

  /**
   * 简单粗暴，直接去掉多余字符，按逗号分割
   */
  public static void function2() {
    String ipAdd = "[\"2.2.2.2\",\"1.1.1.1\",\"6.6.6.6\",\"4.4.4.4\"]";
    List<String> list1 = Arrays
        .asList(ipAdd.replaceAll("\\[|\\]", "").replaceAll("\"", "").split(","));
    for (String s : list1) {
      System.out.println(s);
    }

  }
}
