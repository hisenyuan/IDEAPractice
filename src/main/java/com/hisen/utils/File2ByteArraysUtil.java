package com.hisen.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hisenyuan on 2017/4/18 at 18:27.
 */
public class File2ByteArraysUtil {

  public static byte[] file2Bytes(String path) {
    byte[] buffer = null;
    File file = new File(path);
    try {
      FileInputStream fis = new FileInputStream(file);
      ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
      byte[] b = new byte[1000];
      int n;
      while ((n = fis.read(b)) != -1) {
        bos.write(b, 0, n);
      }
      fis.close();
      bos.close();
      buffer = bos.toByteArray();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return buffer;
  }

  public static void bytes2File(byte[] bytes, String filePath) {
    BufferedOutputStream bos = null;
    FileOutputStream fos = null;
    File file = null;
    try {
      file = new File(filePath);
      BuildFileUtil.buildFile(file);
//      FileImageOutputStream imageOutput = new FileImageOutputStream(file);
//      imageOutput.write(bytes, 0, bytes.length);
//      imageOutput.close();
      file = new File(filePath);
      BuildFileUtil.buildFile(file);
      fos = new FileOutputStream(file);
      bos = new BufferedOutputStream(fos);
      bos.write(bytes);
      bos.flush();
      System.out.println("生成完成");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (bos != null) {
        try {
          bos.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
  }
}
