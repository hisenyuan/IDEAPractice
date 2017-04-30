package com.hisen.mvelem;

import java.util.Arrays;

public class MoveElement {

  public static void main(String[] args) {
    int arr[] = {0, 0, 1, 0, 3, 5, 0, 6, 7};
    System.out.println("移动前:" + Arrays.toString(arr));
    // 移动前:[0, 0, 1, 0, 3, 5, 0, 6, 7]
    mv(arr);
    System.out.println("移动后:" + Arrays.toString(arr));
    // 移动后:[1, 3, 5, 6, 7, 0, 0, 0, 0]
  }

  /**
   * 传入一个数组，把非零数尽量左移
   *
   * @param arr 传入的数组
   */
  static void mv(int arr[]) {
    int i = -1, j, temp;

    for (j = 0; j < arr.length; j++) {
      System.out.println("------->\ti=" + i + "\t j=" + j);
      if (arr[j] != 0) {
        ++i;
        if (i != j) {
          temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      }
    }
  }
/*
 * 移动过程中，i j的变化
 移动前:[0, 0, 1, 0, 3, 5, 0, 6, 7]
------->	i=-1	 j=0
------->	i=-1	 j=1
------->	i=-1	 j=2
------->	i=0	 j=3
------->	i=0	 j=4
------->	i=1	 j=5
------->	i=2	 j=6
------->	i=2	 j=7
------->	i=3	 j=8
移动后:[1, 3, 5, 6, 7, 0, 0, 0, 0]
 */
}
