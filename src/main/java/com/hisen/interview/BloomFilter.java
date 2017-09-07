package com.hisen.interview;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * 布隆过滤器 - 测试某个元素不存在集合中
 * 详细介绍：http://hisen.me/20170907-%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%20%E4%B8%A8%20%E7%AE%80%E4%BB%8B%20-%20Java%20demo/
 * Created by hisenyuan on 2017/9/7 at 8:50.
 */
public class BloomFilter {
  public static final int NUM_SLOTS = 1024 * 1024 * 8;
  public static final int NUM_HASH = 8;
  private BigInteger bitmap = new BigInteger("0");

  public static void main(String[] args) {
    BloomFilter bf = new BloomFilter();
    ArrayList<String> contents = new ArrayList<>();
    contents.add("sldkjelsjf");
    contents.add("ggl;ker;gekr");
    contents.add("wieoneomfwe");
    contents.add("sldkjelsvrnlkjf");
    contents.add("ksldkflefwefwefe");
    for (int i = 0; i < contents.size(); i++) {
      bf.adElement(contents.get(i));
    }
    System.out.println(bf.check("sldkjelsvrnlkjf"));
    System.out.println(bf.check("sldkjelsvrnkjf"));
  }
  private void adElement(String message) {
    for (int i = 0; i < NUM_HASH; i++) {
      int hashCode = getHash(message, i);
      if (!bitmap.testBit(hashCode)) {
        bitmap = bitmap.or(new BigInteger("1").shiftLeft(hashCode));
      }
    }
  }
  private boolean check(String message) {
    for (int i = 0; i < NUM_HASH; i++) {
      int hashCode = getHash(message,i);
      if (this.bitmap.testBit(hashCode)){
        return false;
      }
    }
    return true;
  }

  private int getHash(String message, int i) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("md5");
      message = message + String.valueOf(i);
      byte[] bytes = message.getBytes();
      md5.update(bytes);
      BigInteger bi = new BigInteger(md5.digest());
      return Math.abs(bi.intValue()) % NUM_SLOTS;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return -1;
  }
}
