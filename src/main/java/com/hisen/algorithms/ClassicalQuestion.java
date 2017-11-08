package com.hisen.algorithms;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author : yhx
 * @date : 2017/11/8 23:51
 * @descriptor : 一些简单经典的算法问题
 */
public class ClassicalQuestion {

  /**
   * NO.1
   * 一对小兔兔，出生后第3个月起每个月都生一对兔子
   * 等小兔子长到第四个月后每个月又可以生一对兔子
   * 如果兔子都长生不死，请问每个月的兔子总数是多少？
   *
   * 分析： 兔子总数的规律为数列1,1,2,3,5,8,13,21....
   */
  @Test
  public void countRabbitTest() {
    //输出第四个月兔子的总数
    System.out.println(f(4));
  }

  /**
   * x 的单位为月
   */
  public static int f(int x) {
    if (x == 1 || x == 2) {
      return 1;
    } else {
      return f(x - 1) + f(x - 2);
    }
  }

  /**
   * NO.2
   * 判断1-200之间有多少个素数，且输出所有的素数。、
   */
  @Test
  public void getAllPrimeTest() {
    int sum = 0;
    List<Integer> listNum = new ArrayList<>();
    for (int i = 1; i <= 200; i++) {
      if (i % 2 != 0) {
        sum += 1;
        listNum.add(i);
      }
    }
    System.out.println("素数的个数：" + sum + "\n所有的素数：" + listNum.toString());
  }

  /**
   * NO.3
   * 打印所有的水仙花数
   * 153=1³+5³+3³
   */
  @Test
  public void narcissusNumberTest() {
    int x, y, z, n;
    //立方符号
    final char cube = 0xB3;
    for (n = 100; n < 1000; n++) {
      x = n / 100;
      y = n / 10 % 10;
      z = n % 10;
      int powX = (int) Math.pow(x, 3);
      int powY = (int) Math.pow(y, 3);
      int powZ = (int) Math.pow(z, 3);
      if (n == (powX + powY + powZ)) {
        System.out.println(
            n + "=" + x + cube + "+" + y + cube + "+" + z + cube + "=" + powX + "+" + powY + "+"
                + powZ);
      }
    }
  }
}
