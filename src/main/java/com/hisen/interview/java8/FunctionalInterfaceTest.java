package com.hisen.interview.java8;

/**
 * 函数式接口
 * Created by hisen on 17-8-6.
 */
public class FunctionalInterfaceTest {

  // 声明一个函数式接口
  @FunctionalInterface
  interface Converter<F, T> {

    T convert(F from);
  }

  public static void main(String[] args) {
    // 实现接口
    Converter<String, Integer> converter = (from -> Integer.valueOf(from));
    // 调用函数
    Integer converted = converter.convert("123");
    // 输出
    System.out.println(converted);

    //静态方法引用
    Converter<String, Integer> converter1 = Integer::valueOf;
    Integer con = converter1.convert("234");
    System.out.println(con);

    class Something {

      String startsWith(String s) {
        return String.valueOf(s.charAt(0));
      }
    }

    Something something = new Something();
    Converter<String, String> converter2 = something::startsWith;
    String con2 = converter2.convert("Java");
    System.out.println(con2);    // "J"
  }


}
