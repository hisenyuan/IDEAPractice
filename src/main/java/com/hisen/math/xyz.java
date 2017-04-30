package com.hisen.math;

/**
 * xyz = x3 + y3 + z3
 * 水仙花数
 */
public class xyz {

  public static void main(String[] args) {
    int x, y, z, n;
    char c = 0xB3; //立方根符号
    for (n = 100; n < 1000; n++) {
      x = n / 100;//取出百位
      y = n / 10 % 10;//取出十位
      z = n % 10;//取出个位
      int f = x * x * x + y * y * y + z * z * z;
      if (n == f) {
        System.out.println(
            n + "=" + x + c + "+" + y + c + "+" + z + c + "=" + x * x * x + "+" + y * y * y + "+"
                + z * z * z);
      }
    }
//		全部输出
//		153=1³+5³+3³=1+125+27
//		370=3³+7³+0³=27+343+0
//		371=3³+7³+1³=27+343+1
//		407=4³+0³+7³=64+0+343
  }
}
