package com.hisen.interview;

/*
 * 递归实现字符的翻转
 */
public class ReverseString {

  public static String reverse(String str) {
    if (str == null || str.length() <= 1) {
      return str;
    }
    //str.charAt(0) 截取第一个字符
    //str.substring(1)) 截取第一个字符后的所有字符
    return reverse(str.substring(1)) + str.charAt(0);
  }

  public static void main(String[] args) {
    String str = "hisen";
    System.out.println(reverse(str));
  }
}
