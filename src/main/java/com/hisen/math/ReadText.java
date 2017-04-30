package com.hisen.math;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件读取 输出 + 判断文件编码格式（UTF-8/Unicode/UTF-16BE/GBK）
 * 有些乱码问题待解决
 *
 * @author hisenyuan 2016年3月29日    下午12:18:01
 */
public class ReadText {

  public static void main(String[] args) {
    //相对路径下 前面没有 /
    String file = "src/com/hisen/text/test.txt";
    function1(file);//会乱码
    System.out.println("--------------------左边跟右边不一样长--------------------");
    function2(file);//不会乱码
    System.out.println("--------------------左边跟右边不一样长--------------------");
    System.out.println(codeString(file));//读取文件的编码格式

  }

  public static void function1(String file) {
    BufferedReader bre = null;
    try {
      bre = new BufferedReader(new FileReader(file));
      String str = "";
      while ((str = bre.readLine()) != null) {
        System.out.println(str);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void function2(String file) {
    File f = new File(file);
    InputStream input;
    try {
      input = new FileInputStream(f);
      StringBuffer buffer = new StringBuffer();
      byte[] bytes = new byte[1024];
      for (int n; (n = input.read(bytes)) != -1; ) {
        //codeString(file) 获取文件的编码格式，然后再按原来的格式设置
        buffer.append(new String(bytes, 0, n, codeString(file)));
      }
      System.out.println(buffer);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 判断文件的编码格式
   *
   * @param fileName :file
   * @return 文件编码格式
   */
  @SuppressWarnings("resource")
  public static String codeString(String fileName) {
    BufferedInputStream bin;
    int p = 0;
    try {
      bin = new BufferedInputStream(new FileInputStream(fileName));
      p = (bin.read() << 8) + bin.read();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String code = null;

    switch (p) {
      case 0xefbb:
        code = "UTF-8";
        break;
      case 0xfffe:
        code = "Unicode";
        break;
      case 0xfeff:
        code = "UTF-16BE";
        break;
      default:
        code = "GBK";
    }

    return code;
  }
}
