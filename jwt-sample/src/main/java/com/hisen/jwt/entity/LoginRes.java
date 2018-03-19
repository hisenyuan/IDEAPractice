package com.hisen.jwt.entity;

/**
 * @author : hisenyuan
 * @date : 2018/3/16 23:27
 * @descriptor :
 */
public class LoginRes {
  private String code = "0000";
  private String msg;
  private String jwt;
  private String userName;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
