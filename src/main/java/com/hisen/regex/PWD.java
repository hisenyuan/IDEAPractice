package com.hisen.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配解压密码
 * Created by hisenyuan on 2017/8/8 at 21:27.
 */
public class PWD {

  public static void main(String[] args) {
    String s = "<p>\n"
        + "\t解压密码：www.250sb.cn__1324\n"
        + "</p>";
    //匹配
    String regex = "解压密码.*\\s";
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(s);
    String t = "\\s";
    if (matcher.find()){
      System.out.println(matcher.group());
    }else {
      System.out.println(t);
    }
  }
}
