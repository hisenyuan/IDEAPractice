package com.hisen.api.douban.bean;

/**
 * Created by hisen on 17-9-8.
 * E-mail: hisenyuan@gmail.com
 */
public class Rating {

  /**
   * 最高分
   */
  private int max;
  /**
   * 打分人数
   */
  private int numRaters;
  /**
   * 平均分
   */
  private String average;
  /**
   * 最低分
   */
  private int min;

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public int getNumRaters() {
    return numRaters;
  }

  public void setNumRaters(int numRaters) {
    this.numRaters = numRaters;
  }

  public String getAverage() {
    return average;
  }

  public void setAverage(String average) {
    this.average = average;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }
}
