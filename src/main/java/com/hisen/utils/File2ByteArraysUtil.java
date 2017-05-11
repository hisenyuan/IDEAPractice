package com.hisen.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * Created by hisenyuan on 2017/4/18 at 18:27.
 */
public class File2ByteArraysUtil {
  private static Logger logger = Logger.getLogger(File2ByteArraysUtil.class);
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
      logger.error("错误："+e);
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
      file = new File(filePath);
      BuildFileUtil.buildFile(file);
      fos = new FileOutputStream(file);
      bos = new BufferedOutputStream(fos);
      bos.write(bytes);
      bos.flush();
      System.out.println("生成完成");
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("错误："+e);
    } finally {
      IOUtils.closeQuietly(bos,fos);
    }
  }
}
