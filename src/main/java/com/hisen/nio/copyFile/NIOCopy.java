package com.hisen.nio.copyFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.junit.Test;

/**
 * Created by hisenyuan on 2017/6/1 at 11:28.
 */
public class NIOCopy {

  @Test
  public void testCopy() {
    String oldFileName = "C:\\1\\b\\ubuntu-16.04.2-server-amd64.iso";
    String newFileName = "C:\\1\\c\\ubuntu-16.04.2-server-amd64.iso";
    nioCopy(oldFileName, newFileName);
    ioCopy(oldFileName,newFileName.replace("c","d"));
    /**
     * 得出的数据很诡异，为什么NIO会比IO的时间还要长？
     *
     * 文件大小为：869269504 byte
     * NIO方式复制完成，耗时 9 秒
     *
     * 文件大小为：869269504 byte
     * IO方式复制完成，耗时 7 秒
     */
  }

  /**
   * 利用NIO进行读写文件
   *
   * @param oldFileName 原文件的路径
   * @param newFileName 新文件的路径
   */
  public static void nioCopy(String oldFileName, String newFileName) {
    try {
      FileChannel fileChannelIn = new FileInputStream(new File(oldFileName)).getChannel();
      FileChannel fileChannelOut = new FileOutputStream(new File(newFileName)).getChannel();
      //获取文件大小
      long size = fileChannelIn.size();
      System.out.printf("文件大小为：%s byte \n",size);
      //缓冲
      ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

      long start = System.currentTimeMillis();
      while (fileChannelIn.read(byteBuffer) != -1) {
        //准备写
        byteBuffer.flip();
        fileChannelOut.write(byteBuffer);
        //准备读
        byteBuffer.clear();
      }
      long end = System.currentTimeMillis();
      System.out.printf("NIO方式复制完成，耗时 %s 秒\n",(end-start)/1000);
      //关闭
      fileChannelIn.close();
      fileChannelOut.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * IO方式复制文件
   * @param oldFileName
   * @param newFileName
   */
  public static void ioCopy(String oldFileName, String newFileName) {
    try {
      FileInputStream fileInputStream = new FileInputStream(new File(oldFileName));
      FileOutputStream fileOutputStream = new FileOutputStream(new File(newFileName));

      long length = new File(oldFileName).length();

      System.out.printf("文件大小为：%s byte \n",length);
      byte[] buffer = new byte[1024];

      long start = System.currentTimeMillis();
      int len = 0;
      while ((len = fileInputStream.read(buffer)) != -1){
        fileOutputStream.write(buffer,0,len);
      }
      long end = System.currentTimeMillis();
      System.out.printf("IO方式复制完成，耗时 %s 秒\n",(end-start)/1000);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
