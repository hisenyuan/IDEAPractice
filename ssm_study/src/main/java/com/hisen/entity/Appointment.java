package com.hisen.entity;

/**
 * Created by hisenyuan on 2017/4/6 at 16:56.
 */
import java.util.Date;

public class Appointment {

  private long bookId;// 图书ID

  private long studentId;// 学号

  private Date appointTime;// 预约时间

  // 多对一的复合属性
  private Book book;// 图书实体

  // 省略构造方法，getter和setter方法，toString方法

  @Override
  public String toString() {
    return "Appointment{" +
        "bookId=" + bookId +
        ", studentId=" + studentId +
        ", appointTime=" + appointTime +
        ", book=" + book +
        '}';
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public Date getAppointTime() {
    return appointTime;
  }

  public void setAppointTime(Date appointTime) {
    this.appointTime = appointTime;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}