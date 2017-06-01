package com.hisen.interview;

import java.util.Arrays;
import org.junit.Test;

/**
 * 最简单的方式实现快排
 * Created by hisenyuan on 2017/6/1 at 16:55.
 */
public class QuickSort {

  @Test
  public void test() {
    int[] a = {1, 3, 43, 5, 33, 53, 3, 7, -9, 23, 90};
    int low = 0;
    int high = a.length - 1;
    qiuckSort(a, low, high);
    //快速打印数组
    System.out.println(Arrays.toString(a));
  }

  public static void qiuckSort(int a[], int low, int high) {
    int l = low;
    int r = high;
    int temp = a[l];

    if (l < r) {
      while (l < r) {
        while (l < r && a[r] > temp) {
          r--;
        }
        if (l < r) {
          a[l] = a[r];
        }
        while (l < r && a[l] <= temp) {
          l++;
        }
        if (l < r) {
          a[r] = a[l];
        }
      }
      a[l] = temp;
      qiuckSort(a, low, l - 1);
      qiuckSort(a, l + 1, high);
    }
  }
}
