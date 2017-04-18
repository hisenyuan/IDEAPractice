package com.hisen.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by hisenyuan on 2017/4/18 at 18:33.
 */
public class BuildFileUtil {
  public static void buildFile(File file){
    if (!file.exists()) {
      File parent = file.getParentFile();
      if (parent != null && !parent.exists()) {
        parent.mkdirs();
        System.out.println("创建父目录成功！");
      }
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("新建路径：" + file);
      System.out.println("创建文件完成！");
    }
  }
}
