package com.hisen.String;

/**
 * 半角字符和全角字符的转换 以及 判断
 * Created by hisenyuan on 2017/4/1 at 10:37.
 */
public class FullHalf {

  /**
   * ASCII表中可见字符从!开始，偏移位值为33(Decimal)
   */
  static final char DBC_CHAR_START = 33; // 半角!

  /**
   * ASCII表中可见字符到~结束，偏移位值为126(Decimal)
   */
  static final char DBC_CHAR_END = 126; // 半角~

  /**
   * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
   */
  static final char SBC_CHAR_START = 65281; // 全角！

  /**
   * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
   */
  static final char SBC_CHAR_END = 65374; // 全角～

  /**
   * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
   */
  static final int CONVERT_STEP = 65248; // 全角半角转换间隔

  /**
   * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
   */
  static final char SBC_SPACE = 12288; // 全角空格 12288

  /**
   * 半角空格的值，在ASCII中为32(Decimal)
   */
  static final char DBC_SPACE = ' '; // 半角空格

  public static void main(String[] args) {
    String s = "123456";
    //半角转换成全角字符
    String s1 = bj2qj(s);
    //全角转换成半角
    String s2 = qj2bj(s1);
    System.out.println("全角：" + s1 + " -> 半角：" + s2);
    System.out.println("--------------------------");
    String fh = s1 + s2;
    //判断全角还是半角
    fullOrHalf(fh);
    //打印ASCII表中所有字符
    printAllChar();
  }

  /**
   * <PRE>
   * 半角字符->全角字符转换
   * 只处理空格，!到˜之间的字符，忽略其他
   * </PRE>
   */
  private static String bj2qj(String src) {
    if (src == null) {
      return src;
    }
    StringBuilder buf = new StringBuilder(src.length());
    char[] ca = src.toCharArray();
    for (int i = 0; i < ca.length; i++) {
      if (ca[i] == DBC_SPACE) { // 如果是半角空格，直接用全角空格替代
        buf.append(SBC_SPACE);
      } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) { // 字符是!到~之间的可见字符
        buf.append((char) (ca[i] + CONVERT_STEP));
      } else { // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
        buf.append(ca[i]);
      }
    }
    return buf.toString();
  }

  /**
   * <PRE>
   * 全角字符->半角字符转换
   * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
   * </PRE>
   */
  public static String qj2bj(String src) {
    if (src == null) {
      return src;
    }
    StringBuilder buf = new StringBuilder(src.length());
    char[] ca = src.toCharArray();
    for (int i = 0; i < src.length(); i++) {
      if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内
        buf.append((char) (ca[i] - CONVERT_STEP));
      } else if (ca[i] == SBC_SPACE) { // 如果是全角空格
        buf.append(DBC_SPACE);
      } else { // 不处理全角空格，全角！到全角～区间外的字符
        buf.append(ca[i]);
      }
    }
    return buf.toString();
  }

  /**
   * 使用正则表达式判断字符是否为全角
   */
  public static void fullOrHalf(String str) {
    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      String temp = String.valueOf(chars[i]);
      // 正则判断是全角字符
      if (temp.matches("[^\\x00-\\xff]")) {
        System.out.println("全角 -> " + temp);
      }
      // 判断是半角字符
      else {
        System.out.println("半角 -> " + temp);
      }
    }
  }

  /**
   * 打印所有字符
   */
  public static void printAllChar() {
    for (int i = Character.MIN_VALUE; i <= Character.MAX_VALUE; ++i) {
      System.out.println("ASCII：" + i + " -> " + "字符：" + (char) i);
    }
  }
}
