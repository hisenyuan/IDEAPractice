package com.hisen.utils;

import java.security.MessageDigest;
import org.junit.Test;

/**
 * @author hisenyuan
 * @time 2018/1/23 18:53
 * @description 生成32位的MD5密码
 */
public class MD5Util {

  @Test
  public void md5Test() {
    String text = "hisenyuan";
    String pwd = Md5(text);
    System.out.println(pwd);
  }

  /**
   * md5加密
   */
  private static String Md5(String unSafePwd) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = md5.digest(unSafePwd.getBytes("utf-8"));
      return toHex(bytes);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 从字节数组转换为16进制字符串
   *
   * @param md5Bytes md5加密之后的数据
   */
  private static String toHex(byte[] md5Bytes) {
    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    StringBuilder ret = new StringBuilder(md5Bytes.length * 2);
    for (int i = 0; i < md5Bytes.length; i++) {
      ret.append(HEX_DIGITS[(md5Bytes[i] >> 4) & 0x0f]);
      ret.append(HEX_DIGITS[md5Bytes[i] & 0x0f]);
    }
    return ret.toString();
  }
}
