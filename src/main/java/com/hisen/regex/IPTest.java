package com.hisen.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取ip地址
 * Created by hisenyuan on 2017/8/3 at 14:07.
 */
public class IPTest {

  public static void main(String[] args) {
    String s = "114.245.46.24 - - [02/Aug/2017:06:55:44 +0800] \"POST /cloud/?mod=private HTTP/1.1\" 200 18 \"-\" \"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)\"\n";
    String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(s);
//    System.out.println(matcher.group());
    //必须得先判断，否则会报异常：java.lang.IllegalStateException
    if (matcher.find()) {
      System.out.println(matcher.group());
    }
  }
}
