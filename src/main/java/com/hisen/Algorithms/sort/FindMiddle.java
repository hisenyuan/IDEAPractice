package com.hisen.Algorithms.sort;

import java.util.Random;

/**
 * 找出中位数
 * 1亿个int数
 * 内存：400M
 *
 * @author hisenyuan 2017年1月20日    下午5:37:48
 */
public class FindMiddle {

  public static void main(String[] args) {

    int[] a = new int[100000000];
    Random r = new Random();
    for (int i = 0; i < a.length; i++) {
      a[i] = r.nextInt(100000000) + 1;
    }
    long startTime = System.currentTimeMillis();
    //调用堆排序算法
    HeapSort(a);
    long endTime = System.currentTimeMillis();
    long time = (endTime - startTime) / 1000;
    System.out.println("中位数：" + a[a.length / 2]);
    //Java 虚拟机试图使用的最大内存量 bytes
    long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
    // Java 虚拟机中的空闲内存量 bytes
    long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
    //Java 虚拟机中的内存总量 bytes
    long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
    System.out
        .println("max：" + max + "M--free：" + free + "M--total：" + total + "M--time：" + time + "秒");

  }

  /**
   * 堆排序
   */
  public static void HeapSort(int[] a) {
    int i;
    /* 把a中的r构建成一个大顶堆 */
    for (i = a.length / 2; i > 0; i--) {
      HeapAdjust(a, i, a.length - 1);
    }
    for (i = a.length - 1; i >= 0; i--) {
      Swap(a, 1, i);
      HeapAdjust(a, 1, i - 1);
    }
  }

  /* 调整a[s] 的关键字，使a[s....m]成为一个大顶堆 */
  public static void HeapAdjust(int[] a, int s, int m) {
    int temp, j;
    temp = a[s];
    /* 沿关键字较大的孩子节点向下筛选 */
    for (j = 2 * s; j < m; j *= 2) {
      if (j < m && a[j] < a[j + 1])
        /* j为关键字中较大记录的下标 */ {
        ++j;
      }
      if (temp >= a[j])
				/* 应该插入在s位置 */ {
        break;
      }
      a[s] = a[j];
      s = j;
    }
		/* 插入 */
    a[s] = temp;
  }

  /*
   * 数组数据交换
   */
  public static void Swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
