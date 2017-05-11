package com.hisen.image;

import com.hisen.utils.Base64Util;
import com.hisen.utils.File2ByteArraysUtil;

/**
 * Created by hisenyuan on 2017/5/11 at 18:44.
 */
public class ShowImageByBase64 {
  public static String showimage(){
    //写相对路径会报错，暂时不知道如何解决
    String imagePath = "C:\\1\\830.jpg";
    byte[] bytes = File2ByteArraysUtil.file2Bytes(imagePath);
    String s = Base64Util.encodeBase64(bytes);
    return s;
  }
}
