package com.hisen.utils;

import java.util.Random;

/**
 * Created by hisen on 17-4-22.
 */
public class test {

  public static void main(String[] args) {
    for (int j = 0; j < 100; j++) {
      String s =
          String.valueOf(new Random().nextInt(999999))+","
              + String.valueOf(new Random().nextInt(999999))+","
              + String.valueOf(new Random().nextInt(999999))+","
              + String.valueOf(new Random().nextInt(999999))+",";
      String[] chars = s.split(",");
      StringBuilder name = new StringBuilder();
      for (int i = 0; i < chars.length; j++) {
        name.append((char) Integer.parseInt(chars[i]));
      }
      System.out.println(name.toString());
    }
  }

}
