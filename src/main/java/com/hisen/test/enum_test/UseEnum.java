package com.hisen.test.enum_test;

/**
 * Created by hisenyuan on 2017/8/15 at 17:14.
 */
public class UseEnum {

  public static void main(String[] args) {
    // 输出相关属性
    System.out.println(Color.BLANK.getName());
    System.out.println(Color.BLANK.getIndex());

    /**
     * 遍历所有的信息
     */
    for (Color color : Color.values()){
      System.out.printf("name:%s,index:%s\n",color.getName(),color.getIndex());
    }
  }
}
