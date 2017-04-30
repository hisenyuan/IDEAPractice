package com.hisen.String.split;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String line = "20E WED 01PM 0E";
 * 转换为下面的输出结果
 *
 * @author hisenyuan 2016年4月7日    下午3:16:36
 */
public class SplitString {

  public static void main(String[] args) {
    Pattern p = Pattern.compile("(\\d+)(\\w+)\\s+(\\w+)\\s+(\\d+)(\\w+)\\s+(\\d+)(\\w+)");

    String line = "20E WED 01PM 0E";
    Matcher m = p.matcher(line);
    if (!m.matches()) {
      throw new IllegalArgumentException("Bad input: " + line);
    } else {
      int fDegree = Integer.parseInt(m.group(1));
      String fDegreeEW = m.group(2);
      String day = m.group(3);
      int time = Integer.parseInt(m.group(4));
      String amPm = m.group(5);
      int sDegree = Integer.parseInt(m.group(6));
      String sDegreeEW = m.group(7);

      System.out.println("1：" + fDegree);
      System.out.println("2：" + fDegreeEW);
      System.out.println("3：" + day);
      System.out.println("4：" + time);
      System.out.println("5：" + amPm);
      System.out.println("6：" + sDegree);
      System.out.println("7：" + sDegreeEW);
      //			输出结果
      //			1：20
      //			2：E
      //			3：WED
      //			4：1
      //			5：PM
      //			6：0
      //			7：E
    }

  }
}
