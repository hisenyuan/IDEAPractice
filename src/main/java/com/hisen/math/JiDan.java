package com.hisen.math;

/**
 * 朋友圈遇到的计算一筐鸡蛋不同拿法算最少有多少个的题
 *
 * @author hisenyuan 2017年2月8日    下午3:34:19
 */
public class JiDan {

  public static void main(String[] args) {
    // 1个1个拿，正好拿完。
    // 2个2个拿，还剩1个。
    // 3个3个拿，正好拿完。
    // 4个4个拿，还剩1个。
    // 5个5个拿，还差1个。
    // 6个6个拿，还剩3个。
    // 7个7个拿，正好拿完。
    // 8个8个拿，还剩1个。
    // 9个9个拿，正好拿完。
    //问筐里最少有多少鸡蛋？
    boolean flag = true;
    int x = 0;
    while (flag) {
      if (x % 3 == 0 && x % 7 == 0 && x % 9 == 0) {
        if ((x - 1) % 2 == 0 && (x - 1) % 4 == 0 && (x - 1) % 8 == 0) {
          if ((x + 1) % 5 == 0 && (x - 3) % 6 == 0) {
            System.out.println(x);
            // 1449 为最小的数目
            flag = false;
          }
        }
      }
      ++x;
    }
  }
}
