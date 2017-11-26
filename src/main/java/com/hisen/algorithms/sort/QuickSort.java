package com.hisen.algorithms.sort;

import java.util.Arrays;

/**
 * @author : yhx
 * @date : 2017/11/26 22:29
 * @descriptor : 快速排序
 */
public class QuickSort {

  public static void main(String[] args) {
    int[] array = {10, 9, 8, 4, 5, 6, 7, 2, 1, 3};
    quickSort(array, 0, array.length - 1);
    System.out.println(Arrays.toString(array));
  }

  /**
   * 快速排序
   * @param array
   * @param low
   * @param high
   */
  public static void quickSort(int[] array, int low, int high) {
    int pivot;
    if (low < high) {
      pivot = oneSort(array, low, high);
      quickSort(array, low, pivot - 1);
      quickSort(array, pivot + 1, high);
    }
  }

  /**
   * 一趟排序
   * @param array
   * @param low
   * @param high
   * @return
   */
  public static int oneSort(int[] array, int low, int high) {
    int key;
    key = array[low];
    while (low < high) {
      while (high > low && array[high] >= key) {
        high--;
      }
      swap(array, low, high);
      while (low < high && array[low] <= key) {
        low++;
      }
      swap(array, low, high);
    }
    return low;
  }

  /**
   * 交换位置
   * @param array
   * @param i
   * @param j
   */
  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
