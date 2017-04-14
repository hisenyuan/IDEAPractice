package com.hisen.Reflect.get;

/**
 * Created by hisenyuan on 2017/4/14 at 10:42.
 */
public class Person {

  private String name;
  private int age;
  private String msg = "hello wrold";

  public Person(String name) {
    this.name = name;
    System.out.println(name);
  }

  public Person() {
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void fun() {
    System.out.println("这是一个没有参数的fun方法");
  }

  public void fun(String name, int age) {
    System.out.println("我叫" + name + ",今年" + age + "岁");
  }
}
