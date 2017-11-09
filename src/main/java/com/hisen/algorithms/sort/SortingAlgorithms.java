package com.hisen.algorithms.sort;

import java.util.Arrays;
import org.junit.Test;

/**
 * @author : yhx
 * @date : 2017/11/7 16:24
 * @descriptor : 排序算法
 */
public class SortingAlgorithms {

  /**
   * 最简单的冒泡排序
   */
  @Test
  public void  bubbleSort(){
    int[] numbers = {3, 6, 2, 4, 5, 1, 7, 9, 8};
    int temp = 0;
    int len = numbers.length;
    for (int i = 0; i < len-1; i++) {
      for (int j = 0; j < len - 1 - i ; j++) {
        if (numbers[j]>numbers[j+1]){
          temp = numbers[j];
          numbers[j]=numbers[j+1];
          numbers[j+1]=temp;
        }
      }
    }
    System.out.println(Arrays.toString(numbers));
  }
}
