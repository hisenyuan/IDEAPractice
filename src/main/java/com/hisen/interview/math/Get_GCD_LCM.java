package com.hisen.interview.math;

/**
 * @author : hisenyuan@gmail.com
 * @date : 2017/10/21 11:50
 */
public class Get_GCD_LCM {

  public static void main(String[] args) {
    Get_GCD_LCM getGcdLcm = new Get_GCD_LCM();
    int gcd = getGcdLcm.getGCD(4, 8);
    System.out.println("递归方法，最大公约数：" + gcd + ",最小公倍数："+getGcdLcm.getLCM(4, 8));

    int i = getGcdLcm.divisionGCD(4, 8);
    System.out.println("\n辗转相除，最大公约数：" + i + ",最小公倍数：" + 4 * 8 / i);

    System.out.println("相减法求最大公约数:"+getGcdLcm.subtractionGCD(4,8));
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
   */
  public int getLCM(int m, int n) {
    int mn = m * n;
    return mn / getGCD(m, n);
  }

  /**
   * 辗转相除求最大公约数
   * 有两整数a和b：
   * ① a%b得余数c
   * ② 若c=0，则b即为两数的最大公约数
   * ③ 若c≠0，则a=b，b=c，再回去执行①
   */
  public int divisionGCD(int m, int n) {
    int a;
    while (n != 0) {
      a = m % n;
      m = n;
      n = a;
    }
    return m;
  }
  /**
   * 相减法求最大公约数
   * 有两整数a和b：
   * ① 若a>b，则a=a-b
   * ② 若a<b，则b=b-a
   * ③ 若a=b，则a（或b）即为两数的最大公约数
   * ④ 若a≠b，则再回去执行①
   */
  public int subtractionGCD(int m,int n){
    while(m != n){
      if (m>n){
        m = m-n;
      }else {
        n = n - m;
      }
    }
    return m;
  }

}
