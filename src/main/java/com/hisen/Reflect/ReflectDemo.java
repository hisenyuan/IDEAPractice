package com.hisen.Reflect;

/**
 * 三种获取反射累的方法
 * Created by hisenyuan on 2017/4/14 at 10:37.
 */
public class ReflectDemo {

  public static void main(String[] args) throws ClassNotFoundException {
    //第一种：Class c1 = Code.class;
    Class class1 = ReflectDemo.class;
    System.out.println(class1.getName());

    //第二种：Class c2 = code1.getClass();
    ReflectDemo demo2 = new ReflectDemo();
    Class c2 = demo2.getClass();
    System.out.println(c2.getName());

    //第三种：Class c3 = Class.forName("com.trigl.reflect.Code");
    Class class3 = Class.forName("com.hisen.Reflect.ReflectDemo");
    System.out.println(class3.getName());
  }
}
