package com.hisen.entity;

/**
 * Created by hisenyuan on 2017/4/6 at 16:58.
 */
public class Book {

  private long bookId;// 图书ID

  private String name;// 图书名称

  private int number;// 馆藏数量

  // 省略构造方法，getter和setter方法，toString方法

  @Override
  public String toString() {
    return "Book{" +
        "bookId=" + bookId +
        ", name='" + name + '\'' +
        ", number=" + number +
        '}';
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
