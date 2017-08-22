package com.hisen.String.split;

/**
 * 手机号中间四位脱敏
 * Created by hisenyuan on 2017/8/22 at 19:24.
 */
public class ReplacePhoneNumber {

  public static void main(String[] args) {
    String p = "15520080808";
    String s = p.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    System.out.println(s);
  }
}
