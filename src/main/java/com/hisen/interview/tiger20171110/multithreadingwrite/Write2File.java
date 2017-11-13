package com.hisen.interview.tiger20171110.multithreadingwrite;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.joda.time.DateTime;

/**
 * @author : yhx
 * @date : 2017/11/13 16:40
 * @descriptor : 利用Guava向文件写入数据 - 加入同步方法
 */
public class Write2File {
  // 获取系统换行符
  private static String lineSeparator = System.getProperty("line.separator", "\n");
  // 静态方法同步 - 同步在拥有该方法的对象上
  public static synchronized void write2file(){
    File file = new File("C:\\1\\hisenyuan\\write.txt");
    // 三个系统都没有统一的换行符
    String str = "Hello world - " + DateTime.now().toString("yyyy-MM-dd hh:mm:ss SSS") + lineSeparator;
      try {
        Files.append(str, file, Charsets.UTF_8);
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

  // 实例方法同步 - 同步在拥有该方法的对象上
  public synchronized void write2file1(){
    File file = new File("C:\\1\\hisenyuan\\write1.txt");
    String str = "Hello world 1 - " + DateTime.now().toString("yyyy-MM-dd hh:mm:ss SSS") + lineSeparator;
    try {
      Files.append(str, file, Charsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
