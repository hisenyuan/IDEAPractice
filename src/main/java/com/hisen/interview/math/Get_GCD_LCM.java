package com.hisen.interview.math;

/**
 * @author : hisenyuan@gmail.com
 * @date : 2017/10/21 11:50
 */
public class Get_GCD_LCM {

  public static void main(String[] args) {
    Get_GCD_LCM getGcdLcm = new Get_GCD_LCM();
    int gcd = getGcdLcm.getGCD(4, 8);
    System.out.println("最大公约数：" + gcd);
    int lcm = getGcdLcm.getLCM(4, 8);
    System.out.println("最小公倍数：" + lcm);
  }

  /**
   * 最大公约数
   */
  public int getGCD(int m, int n) {
    if (n == 0) {
      return m;
    }
    return getGCD(n, m % n);
  }

  /**
   * 最小公倍数
   * @param m
   * @param n
   * @return
   */
  public int getLCM(int m, int n) {
    int mn = m * n;
    return mn / getGCD(m, n);
  }
}
