package com.hisen.thinkingInJava.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by hisen on 17-5-6.
 * 对前面的一种改进，程序变得更小
 */
public class DirList3 {

  public static void main(String[] args) {
    File path = new File(".");
    String[] list;
    if (args.length==0)
      list=path.list();
    else
      list=path.list(new FilenameFilter() {
        private Pattern pattern=Pattern.compile(args[0]);
        @Override
        public boolean accept(File dir, String name) {
          return pattern.matcher(name).matches();
        }
      });
    //按字母进行排序
    Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
    for (String dirItem:list) {
      System.out.println(dirItem);
    }
  }
}
