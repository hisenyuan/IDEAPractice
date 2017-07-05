package com.hisen.interview;

/**
 * 有时候可能会遇到关于String 的一些问题
 * Created by hisenyuan on 2017/7/5 at 1:09.
 */
public class AboutNewObject {


  public static void main(String[] args) {
    User m = new User();
    User w = new User();
    m.setAdd("北京");
    System.out.println(w.getAdd());

    String a = "xxx";
    //b指向a所指向的同一个对象
    String b = "xxx";
    //c是一个新对象
    String c = new String("xxx");
    System.out.println(a == b);
    System.out.println(a == c);
    //c.intern()，取c的值
    System.out.println(a == c.intern());
  }

  static class User {

    private String name;
    private String add;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAdd() {
      return add;
    }

    public void setAdd(String add) {
      this.add = add;
    }
  }
}
