package com.hisen.mybatisStudy.po;

import java.util.Date;

/**
 * Created by hisen on 17-3-25.
 */
public class User {

  private int id;
  private String username;// 用户姓名
  private String sex;// 性别
  private Date birthday;// 生日
  private String address;// 地址

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", sex='" + sex + '\'' +
        ", birthday=" + birthday +
        ", address='" + address + '\'' +
        '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
