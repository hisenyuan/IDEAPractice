package com.hisen.interview.math;

public class MaopaoSort {

  public static void main(String[] args) {
    //int hisen[] = new int[10];
    int hisen[] = {1, 23, 4, 9, 7, 22, 54, 89, 47};
    for (int i = 1; i < hisen.length; i++) {
      //从数组最后一个开始，往前逐个比较，符合条件就调换顺序
      for (int j = hisen.length - 1; j > 0; j--) {
        //大于号是降序，小于号是升序
        if (hisen[j] > hisen[j - 1]) {
          int temp = hisen[j];
          hisen[j] = hisen[j - 1];
          hisen[j - 1] = temp;
        }
      }
    }
    //输出排序之后的数组
    for (int i = 0; i < hisen.length; i++) {
      System.out.println(hisen[i]);
    }
  }
}
