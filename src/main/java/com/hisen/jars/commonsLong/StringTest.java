package com.hisen.jars.commonsLong;

import org.apache.commons.lang3.StringUtils;

/**
 * Commons Lang是对JDK中java.lang包的补充
 * 避免重复造轮子
 * Created by hisenyuan on 2017/7/27 at 15:24.
 */
public class StringTest {

  public static void main(String[] args) {
    String hisen = " ";
    //字符串判空
    //isBlank  判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
    System.out.println(StringUtils.isBlank(hisen));//空格也会认为是空
    //isEmpty  判断某字符串是否为空，为空的标准是 str==null或 str.length()==0
    System.out.println(StringUtils.isEmpty(hisen));//不能过滤空格

    System.out.println(">>>>> trim <<<<<");
    //trim - 过滤前后的空格 中间的不会过滤
    System.out.println(StringUtils.trim(null)); // null
    System.out.println(StringUtils.trim("")); // ""
    System.out.println(StringUtils.trim("     ")); // ""
    System.out.println(StringUtils.trim("abc")); // "abc"
    System.out.println(StringUtils.trim("    abc")); // "abc"
    System.out.println(StringUtils.trim("    abc  ")); // "abc"
    System.out.println(StringUtils.trim("    ab c  ")); // "ab c"
  }
}
