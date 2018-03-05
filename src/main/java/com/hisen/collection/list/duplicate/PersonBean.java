package com.hisen.collection.list.duplicate;

/**
 * @author hisenyuan
 * @time 2018/2/7 12:39
 * @description
 */
public class PersonBean {
  private int age;
  private String name;
  private int weight;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWeight() {
    return weight;
  }

  public PersonBean() {
  }

  public PersonBean(int age, String name, int weight) {
    this.age = age;
    this.name = name;
    this.weight = weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
