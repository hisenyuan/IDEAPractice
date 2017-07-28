package com.hisen.jars.apache.commons.lang;

import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by hisenyuan on 2017/7/28 at 10:42.
 */
public class LangDemo {

  private static Logger log = Logger.getLogger(LangDemo.class);

  @Test
  public void charSetDemo() {
    log.info(">>>>> charSet Demo <<<<<");
    //包含这个字符串中单个字符
    CharSet charSet = CharSet.getInstance("abcd");
    String demoStr = "The quick brown fox jumps over the lazy dog.abcd";
    int count = 0;
    for (int i = 0; i < demoStr.length(); i++) {
      if (charSet.contains(demoStr.charAt(i))) {
        count++;
      }
    }
    log.info("count:" + count);
  }

  @Test
  public void charSetUtilsDemo() {
    log.info(">>>>> CharSetUtils Demo <<<<<");
    log.info(">>>>> 计算字符串中包含某字符数:" + CharSetUtils.count("time heals all sorrows", "h"));//1
    log.info(">>>>> 删除字符串中包含的某字符:" + CharSetUtils
        .delete("time heals all sorrows", "h"));//time eals all sorrows
    log.info(">>>>> 保留字符串中某字符:" + CharSetUtils.keep("time heals all sorrows", "h"));//h
    log.info(">>>>> 合并连续出现的字符:" + CharSetUtils
        .squeeze("time heals all sorrows", "l"));//time heals al sorrows
  }

  @Test
  public void randomStringUtilsDemo() {
    log.info(">>>>> RandomStringUtils Demo <<<<<");
    log.info(">>>>> 生成指定长度的随机字符串：" + RandomStringUtils.random(50));//中文环境下是乱码
    log.info(">>>>> 在指定字符串中生成长度为n的随机字符串：" + RandomStringUtils
        .random(10, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));//9FURgzeh71
  }

  @Test
  public void stringUtilsDemo() {
    String hisen = " ";
    //字符串判空
    //isBlank  判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
    log.info(">>>>> 字符串判空 isBlank:" + StringUtils.isBlank(hisen));//空格也会认为是空
    //isEmpty  判断某字符串是否为空，为空的标准是 str==null或 str.length()==0
    log.info(">>>>> 字符串判空 isEmpty:" + StringUtils.isEmpty(hisen));//不能过滤空格

    //trim - 过滤前后的空格 中间的不会过滤
    log.info(">>>>> trim:" + StringUtils.trim(null));// null
    log.info(">>>>> trim:" + StringUtils.trim(""));// ""
    log.info(">>>>> trim:" + StringUtils.trim("     "));// ""
    log.info(">>>>> trim:" + StringUtils.trim("abc"));// "abc"
    log.info(">>>>> trim:" + StringUtils.trim("    abc"));// "abc"
    log.info(">>>>> trim:" + StringUtils.trim("    abc  "));// "abc"
    log.info(">>>>> trim:" + StringUtils.trim("    ab c  ")); // "ab c"
  }
}
