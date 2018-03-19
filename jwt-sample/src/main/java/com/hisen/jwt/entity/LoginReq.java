package com.hisen.jwt.entity;

/**
 * @author : hisenyuan
 * @date : 2018/3/16 23:04
 * @descriptor :
 */
public class LoginReq {
  private String userName;
  private String pwd;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
}
