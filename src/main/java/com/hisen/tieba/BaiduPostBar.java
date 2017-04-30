package com.hisen.tieba;

public class BaiduPostBar {

  private int num;
  private String title;
  private String content;
  private String time;

  /* 属性封装 */
  public BaiduPostBar(int num, String title, String content, String time) {
    super();
    this.num = num;
    this.title = title;
    this.content = content;
    this.time = time;
  }

  /* getter 和 setter */
  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return num + "\n主帖标题：" + title + "\n主帖内容：" + content + "\n发帖时间：" + time;
  }


}
