package com.hisen.utils;

import java.io.File;

/**
 * Created by hisenyuan on 2017/4/20 at 16:55.
 */
public class GetAllFileUtil {

  public static void main(String[] args) {
    String filePath = "c:" + File.separator + "1" + File.separator + "hisenyuan";
    String replace = filePath + "\\";
    getAllFile(filePath,replace);
  }

  /**
   * 获取当前文件夹下所有的文件名
   *
   * @param filePath 文件夹路径
   * @param replace 文件夹路径\（最后控制是否输出相对路径）
   */
  public static void getAllFile(String filePath, String replace) {
    replace = replace == null ? "" : replace;
    File file = new File(filePath);
    if (file.exists()) {
      File[] files = file.listFiles();
      if (files == null) {
        System.out.println("文件夹为空");
      } else {
        for (File file2 : files) {
          if (file2.isDirectory()) {
            //System.out.println("文件夹："+file2.getAbsolutePath());
            getAllFile(file2.getAbsolutePath(), replace);
          } else {
            String replace1 = file2.getAbsolutePath().replace(replace, "");
            //System.out.println("替换前："+file2.getAbsolutePath());
            System.out.println("替换后：" + replace1);
          }
        }
      }
    }
  }

}
