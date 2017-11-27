package com.hisen.collection.list.sortlistbycomparator;

/**
 * @author : yhx
 * @date : 2017/11/26 20:51
 * @descriptor :
 */
public class Student {

  /**
   * 姓名
   */
  private String name;
  /**
   * 年龄
   */
  private int age;
  /**
   * 地址
   */
  private String address;
  /***
   * 考试得分
   */
  private int score;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", address='" + address + '\'' +
        ", score=" + score +
        '}';
  }

  /**
   * 静态比较方法
   * @param h1
   * @param h2
   * @return
   */
  public static int compareByNameThenAge(Student h1, Student h2) {
    // 如果名字相同，则再按年龄排序
    if (h1.getName().equals(h2.getName())) {
      return Integer.compare(h1.getAge(), h2.getAge());
    }
    // 默认按名字排序
    return h1.getName().compareTo(h2.getName());
  }
}
