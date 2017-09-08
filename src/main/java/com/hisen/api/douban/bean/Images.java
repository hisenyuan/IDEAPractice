package com.hisen.api.douban.bean;

/**
 * 图书封面URL
 * Created by hisen on 17-9-8.
 * E-mail: hisenyuan@gmail.com
 */
public class Images {

  /**
   * 小
   */
  private String small;
  /**
   * 大
   */
  private String large;
  /**
   * 中
   */
  private String medium;

  public String getSmall() {
    return small;
  }

  public void setSmall(String small) {
    this.small = small;
  }

  public String getLarge() {
    return large;
  }

  public void setLarge(String large) {
    this.large = large;
  }

  public String getMedium() {
    return medium;
  }

  public void setMedium(String medium) {
    this.medium = medium;
  }
}
