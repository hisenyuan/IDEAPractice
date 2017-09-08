package com.hisen.api.douban.bean;

/**
 * 标签
 * Created by hisen on 17-9-8.
 * E-mail: hisenyuan@gmail.com
 */
public class Tag {

  /**
   * 标签次数
   */
  private int count;
  /**
   * 标签名字
   */
  private String name;
  /**
   * 标签标题
   */
  private String title;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
