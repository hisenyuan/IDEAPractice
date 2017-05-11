package com.hisen.thinkingInJava.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by hisen on 17-5-6.
 * thinking in java 第十八章 java i/o 系统
 * 901
 */
public class DirList {

  //input：{args:"/home/hisen/IdeaProjects/IDEAPractice"} 就是当前工程所在根目录
  public static void main(String[] args) {
    //当前工程所在的根目录
    File path = new File(".");
    String[] list;
    if (args.length == 0) {
      list = path.list();
    } else {
      list = path.list(new DirFilter(args[0]));
    }
    for (String dirItem : list) {
      System.out.println(dirItem);
    }
    /* OutPut：可以看出并没有输出子目录
      src
      pom.xml
      IDEAPractice.iml
      .idea
      .git
      ssm_study
      target
      README.md
     */
  }

  static class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
      pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
      return pattern.matcher(name).matches();
    }
  }
}
