package com.hisen.interview;

/**
 * String为final类型，不可变
 * Created by hisen on 17-8-15.
 * E-mail: hisenyuan@gmail.com
 */
public class FinalString {

  private String host = "";
  private String api = "login";
  private String service = host + api;

  public FinalString setHost(String host) {
    this.host = host;
//    System.out.println("A setHost:"+this.services);
//    System.out.println("A setHost:"+this.host);
    return this;
  }

  public String getService() {
    return this.service;
  }
  public String getHost(){
    return this.host;
  }

  public static void main(String[] args) {
    String host_ = "127.0.0.1/";
    System.out.println(new FinalString().setHost(host_).getService());
//    login # 因为service所指向的内存地址始终没变
    System.out.println(new FinalString().setHost(host_).getHost());
//    127.0.0.1/
  }
}
