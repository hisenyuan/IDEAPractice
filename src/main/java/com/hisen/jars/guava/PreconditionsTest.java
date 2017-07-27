package com.hisen.jars.guava;

import static com.google.common.base.Preconditions.*;

/**
 * [Google Guava] 1.2-前置条件
 * Created by hisenyuan on 2017/7/27 at 13:58.
 */
public class PreconditionsTest {

  public static void main(String[] args) {
    try {
      checkArgument(true); // false:IllegalArgumentException
      checkNotNull(1);//null:java.lang.NullPointerException
      checkElementIndex(5, 6);//index:5,size:4  java.lang.IndexOutOfBoundsException: index (5) must be less than size (4)
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
