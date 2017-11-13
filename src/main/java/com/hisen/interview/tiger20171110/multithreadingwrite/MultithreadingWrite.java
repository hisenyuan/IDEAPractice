package com.hisen.interview.tiger20171110.multithreadingwrite;

/**
 * @author : yhx
 * @date : 2017/11/13 16:53
 * @descriptor : 写文件的线程类
 */
public class MultithreadingWrite extends Thread{
  Write2File write2File = new Write2File();
  @Override
  public void run(){
    Write2File.write2file();
    write2File.write2file1();
  }
}
