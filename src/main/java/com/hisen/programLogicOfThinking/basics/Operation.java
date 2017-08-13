package com.hisen.programLogicOfThinking.basics;

/**
 * Created by hisen on 17-8-13.
 * E-mail: hisenyuan@gmail.com
 */
public class Operation {

  public static void main(String[] args) {
    //2147483647是int能表示的最大值
    int a = 2147483647 * 2;//结果 -2
    // 正确姿势
    long a1 = 2147483647 * 2L;
    System.out.printf("错误：%s,正确：%s\n", a, a1);
    // 错误：-2,正确：4294967294

    // 整数相除不是四舍五入，而是取整
    double d = 10 / 4;
    // 正确姿势
    double d1 = 10 / 4.0;
    double d2 = 10 / (double) 4;
    System.out.printf("错误：%s,正确：%s,正确：%s\n", d, d1, d2);
    // 错误：2.0,正确：2.5,正确：2.5

    /**
     * 小数计算结果不精确
     * 这需要理解float和double的二进制表示
     */
    float f = 0.1f * 0.1f;
    double dd = 0.1 * 0.1;
    System.out.printf("惊人的结果f = %s,dd = %s\n", f, dd);
    // 惊人的结果f = 0.010000001,dd = 0.010000000000000002

    /**
     * 比较
     * == 判断的是对象是否相同，而不是内容是否相同
     */
    int[] arrA = new int[] {1,2,3};
    int[] arrB = new int[] {1,2,3};
    System.out.println(arrA==arrB);//false

    /**
     * 小结
     * 正整数相乘的结果居然出现了负数
     * 非常基本的小数运算结果居然不精确
     * 字符类型怎么也可以进行算术运算和比较
     */
  }
}
