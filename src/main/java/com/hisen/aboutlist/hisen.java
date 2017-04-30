package com.hisen.aboutlist;

import java.util.ArrayList;
import java.util.List;

public class hisen {

  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    list.add("1.txt");
    list.add("2.txt");
    list.add("3.txt");
    String s = listToString(list, "|");
    String h = "";
    for (String yhx : list) {
      h += "|" + yhx;
    }
    System.out.println(s);//		1.txt|2.txt|3.txt
    System.out.println(h);//	|1.txt|2.txt|3.txt
  }

  /**
   * List转字符串并且加入分隔符的方法
   *
   * @param separator 分隔符
   */
  public static String listToString(List list, String separator) {
    String sb = "";
    for (int i = 0; i < list.size(); i++) {
      sb += list.get(i) + separator;
    }
    /**
     * 去掉多余的分隔符，截取想要的
     * 去掉最后那个 |  ：substring(1, sb.toString().length());
     * 如果多余的分隔符在最后一位：substring(0, sb.toString().length()-1);
     */
    return sb.substring(0, sb.toString().length() - 1);
  }
}