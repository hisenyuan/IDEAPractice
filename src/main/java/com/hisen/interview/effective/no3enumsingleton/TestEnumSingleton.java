package com.hisen.interview.effective.no3enumsingleton;

/**
 * @author : yhx
 * @date : 2018/1/27 23:11
 * @descriptor :
 */
public class TestEnumSingleton {

  public static void main(String[] args) {
    EnumSingleton singleton = EnumSingleton.getInstance();
    EnumSingleton instance = EnumSingleton.getInstance();
    System.out.println(singleton==instance);
  }
}
