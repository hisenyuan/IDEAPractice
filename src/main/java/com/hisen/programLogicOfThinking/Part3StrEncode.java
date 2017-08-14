package com.hisen.programLogicOfThinking;

import java.io.UnsupportedEncodingException;

/**
 * Created by hisen on 17-8-14.
 * E-mail: hisenyuan@gmail.com
 */
public class Part3StrEncode {

  public static void main(String[] args) {
    /**
     * 各地使用的编码
     * Ascii码是基础，一个字节表示，最高位设为0，其他7位表示128个字符。其他编码都是兼容Ascii的，最高位使用1来进行区分。
     * 西欧主要使用Windows-1252，使用一个字节，增加了额外128个字符。
     * 中文大陆地区的三个主要编码GB2312，GBK，GB18030，有时间先后关系，表示的字符数越来越多，且后面的兼容前面的，GB2312和GBK都是用两个字节表示，而GB18030则使用两个或四个字节表示。
     * 香港台湾地区的主要编码是Big5。
     *
     * UTF-32：就是字符编号的整数二进制形式，四个字节。
     * UTF-16使用变长字节表示，最少需要2个字节
     * UTF-8就是使用变长字节表示，每个字符使用的字节个数与其Unicode编号的大小有关，编号小的使用的字节就少，编号大的使用的字节就多，使用的字节个数从1到4个不等。
     */

    /**
     * 乱码恢复
     */
    String str = "ÀÏÂí";
    try {
      String gb18030 = new String(str.getBytes("windows-1252"), "GB18030");
      System.out.println("ÀÏÂí 转码后：" + gb18030);
      // 暴力破解
      recover(str);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    /**
     * char的本质：是一个数字
     *
     * 为什么字符类型也可以进行算术运算和比较？
     *
     * char本质上是一个固定占用两个字节的无符号正整数
     * 这个正整数对应于Unicode编号，用于表示那个Unicode编号对应的字符。
     *
     * 由于固定占用两个字节，char只能表示Unicode编号在65536以内的字符，而不能表示超出范围的字符。
     *
     * 下面：2，3，4，5都是一样的，本质都是将Unicode编号39532赋给了字符
     */
    char c1 = 'A';//将一个能用Ascii码表示的字符赋给一个字符变量
    char c2 = '马';
    char c3 = 39532;//'马'对应的Unicode编号是39532
    char c4 = 0x9a6c;//16进制常量赋给字符
    char c5 = '\u9a6c';//Unicode字符形式


    //用Integer的方法查看二进制
    System.out.println(Integer.toBinaryString(c2));

    /**
     * 小结
     * 本节介绍了char的本质，它固定占用两个字节，实际上是一个整数，
     * 表示字符的Unicode编号，不在65536编号内的字符一个char表示不了，
     * 需要用两个char。
     */

  }

  /**
   * 暴力破解编码，有12种组合
   */
  public static void recover(String string) throws UnsupportedEncodingException {
    String[] charSet = new String[]{"windows-1252", "GB18030", "Big5", "UTF-8"};
    for (int i = 0; i < charSet.length; i++) {
      for (int j = 0; j < charSet.length; j++) {
        if (i != j) {
          String s = new String(string.getBytes(charSet[i]), charSet[j]);
          System.out.printf("原来编码A假设是：%s,被错误地用编码B解读：%s\n", charSet[i], charSet[j]);
          System.out.printf("乱码：%s,尝试转码：%s\n", string, s);
        }
      }
    }
  }
}
