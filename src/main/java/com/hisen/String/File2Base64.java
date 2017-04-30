package com.hisen.String;

import com.hisen.utils.Base64Util;
import com.hisen.utils.File2ByteArraysUtil;

/**
 * Created by hisenyuan on 2017/4/18 at 18:13.
 */
public class File2Base64 {

  public static void main(String[] args) {
    byte[] bytes = File2ByteArraysUtil.file2Bytes("src/main/java/com/hisen/String/text/tomcat.png");
    //图片加密为base64
    String s = Base64Util.encodeBase64(bytes);
    System.out.println("图片经Base64加密后的字符：" + s);
    //解密
    byte[] s1 = Base64Util.decodeBase64(s);
    //字符串转换回图片
    File2ByteArraysUtil.bytes2File(s1, "src/main/java/com/hisen/String/text/build.png");
  }

}
