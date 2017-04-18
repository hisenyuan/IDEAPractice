package com.hisen.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64加密解密工具类
 * Created by hisenyuan on 2017/4/18 at 10:40.
 */
public class Base64Util {
  public Base64Util() {
  }

  /**
   * commons-codec加密
   * @param str
   * @return
   */
  public static byte[] encode(byte[] str) {
    byte[] result = null;
    if(str != null) {
      result = Base64.encodeBase64(str);
    }

    return result;
  }

  /**
   * commons-codec解密
   * @param str
   * @return
   */
  public static byte[] decode(byte[] str) {
    byte[] result = null;
    if(str != null) {
      result = Base64.decodeBase64(str);
    }

    return result;
  }

  /**
   * sun.misc.BASE64Encoder 加密
   * @param str
   * @return
   */
  public static String encodeBase64(byte[] str) {
    if(str == null) {
      return null;
    } else {
      BASE64Encoder encoder = new BASE64Encoder();

      try {
        return encoder.encode(str);
      } catch (Exception var3) {
        return null;
      }
    }
  }

  /**
   * sun.misc.BASE64Encoder解密
   * @param str
   * @return
   */
  public static byte[] decodeBase64(String str) {
    if(str == null) {
      return null;
    } else {
      BASE64Decoder decoder = new BASE64Decoder();

      try {
        return decoder.decodeBuffer(str);
      } catch (Exception var3) {
        return null;
      }
    }
  }
}
