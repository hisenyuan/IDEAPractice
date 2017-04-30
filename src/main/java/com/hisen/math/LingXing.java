package com.hisen.math;

/**
 * 观察规律，打印菱形的话是由空格跟*组成
 * 每一行里面 空格个数+星号的个数都是相等的
 * 所以在for循环里面加入两个并行的循环可以实现打印
 */
public class LingXing {

  public static void main(String[] args) {
    printLX(5);
  }

  /**
   * 打印菱形的方法
   *
   * @param n 菱形最宽处*的个数
   */
  public static void printLX(int n) {
    // 菱形的上半部分
    for (int i = 1; i <= n; i++) {
      for (int k = 0; k < n - i; k++) {
        System.out.print("\t");
      }
      for (int j = 1; j <= i; j++) {
        System.out.print("\t*\t");
      }
      System.out.println("");
    }
    // 菱形下半部分，x从1开始，否则会多一行
    for (int x = 1; x <= n; x++) {
      for (int y = 0; y < x; y++) {
        System.out.print("\t");
      }
      for (int z = 1; z <= n - x; z++) {
        System.out.print("\t*\t");
      }
      System.out.println("");
    }

  }
}
