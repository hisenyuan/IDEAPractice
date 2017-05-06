package com.hisen.thinkingInJava.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by hisen on 17-5-6.
 */
public class DirList2 {
  public static FilenameFilter filter(final String regex){
    //creation of anonymous inner class
    return new FilenameFilter() {
      private Pattern pattern=Pattern.compile(regex);
      @Override
      public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
      }
    };//匿名类部类结束
  }

  public static void main(String[] args) {
    File path = new File(".");
    String[] list;
    if (args.length==0)
      list=path.list();
    else
      list=path.list(filter(args[0]));
    //按字母进行排序
    Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
    for (String dirItem:list) {
      System.out.println(dirItem);
    }
  }
}
