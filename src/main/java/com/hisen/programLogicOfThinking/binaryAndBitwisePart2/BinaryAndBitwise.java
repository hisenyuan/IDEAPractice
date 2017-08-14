package com.hisen.programLogicOfThinking.binaryAndBitwisePart2;

/**
 * Created by hisenyuan on 2017/8/14 at 17:39.
 */
public class BinaryAndBitwise {

  public static void main(String[] args) {
    int a = 123;
    System.out.println(Integer.toBinaryString(a)); //二进制
    System.out.println(Integer.toHexString(a));  //十六进制

    int b = 0x7B;//java不支持直接使用二进制
    System.out.println("16进制：0x7B = " + b);

    /**
     * 位移运算
     * 右移 * 2^-n ： 做除法
     * 左移 * 2^n ： 做乘法
     */
    int c = 8;
    int i = c >> 2;
    int i1 = c << 2;
    System.out.printf("c >> 2 = %s\n,c << 2 = %s\n", i, i1);

    /**
     * 按位与 &：两位都为1才为1
     * 按位或 |：只要有一位为1，就为1
     * 按位取反 ~： 1变为0，0变为1
     * 按位异或 ^ ：相异为真，相同为假
     */
    boolean b1 = true;
    boolean b2 = false;
    System.out.printf("\nb1 & b2 = %s\nb1 | b2 = %s\nb1 ^ b2 = %s\n", b1 & b2, b1 | b2, b1 ^ b2);

    /**
     * 可以精确表示为2的某次方之和的数可以精确表示，其他数则不能精确表示。
     * 2^(-1) = 0.5
     * 2^(-2) = 0.25
     * 2^(-3) = 0.125
     * 2^(-4) = 0.0625
     *
     * 在误差足够小的时候，结果看上去是精确的，但不精确其实才是常态。
     *
     * 要精确可以先转换为整数，计算之后再转回去。
     * 使用BigDecimal会更准确，但是效率低
     */
    System.out.println("\n0.1f+0.1f = " + (0.1f + 0.1f));
    System.out.println("0.1f*0.1f = " + 0.1f * 0.1f);
    /**
     * 小结
     * 很多小数计算机中不能精确表示导致计算不准确
     * 计算机的基本思维是二进制的
     */
  }
}
