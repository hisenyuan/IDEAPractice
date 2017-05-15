package com.hisen.jars.jmx;

/**
 * 实现接口
 * Created by hisenyuan on 2017/5/15 at 20:04.
 */
public class Hello implements HelloMBean {

  private String name;
  private String age;

  @Override
  public String getName() {
    System.out.println("get name 123");
    return name;
  }

  @Override
  public void setName(String name) {
    System.out.println("set name 123");
    this.name = name;
  }

  @Override
  public String getAge() {
    System.out.println("get age 123");
    return age;
  }

  @Override
  public void setAge(String age) {
    System.out.println("set age 123");
    this.age = age;
  }

  @Override
  public void helloWorld() {
    System.out.println("hello world");
  }

  @Override
  public void helloWorld(String str) {
    System.out.println("helloWorld:" + str);
  }

  @Override
  public void getTelephone() {
    System.out.println("get Telephone");
  }
}
