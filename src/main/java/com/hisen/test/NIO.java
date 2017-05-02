package com.hisen.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import org.junit.Test;

/**
 * Created by hisenyuan on 2017/4/24 at 10:34.
 */
public class NIO {

  @Test
  public void readNIO() {
    String filePath = "c:" + File.separator + "1" + File.separator + "_20170413.txt";
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(new File(filePath));
      FileChannel channel = fis.getChannel();
      int capacity = 100;
      ByteBuffer bf = ByteBuffer.allocate(capacity);
      System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity() + "位置是：" + bf.position());
      int length = -1;
      while ((length = channel.read(bf)) != -1) {
        bf.clear();
        byte[] bytes = bf.array();
        System.out.write(bytes, 0, length);
        System.out.println();
        System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity() + "位置是：" + bf.position());
      }
      channel.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Test
  public void writeNIO(){
    String filename = "c:" + File.separator + "1" + File.separator + "out.txt";
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(new File(filename));
      FileChannel channel = fos.getChannel();
      for (int i = 0; i < 1000; i++) {
        ByteBuffer byteBuffer= Charset.forName("utf-8").encode("低头要有勇气,抬头要有底气");
        // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
        System.out.println("初始化容量和limit：" + byteBuffer.capacity() + "," + byteBuffer.limit());
        int length = 0;
        while ((length=channel.write(byteBuffer))!=0){
          System.out.println("写入的长度："+length);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
