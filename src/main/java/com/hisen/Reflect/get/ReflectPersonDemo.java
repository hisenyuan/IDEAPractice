package com.hisen.Reflect.get;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 * 被反射的类一定要有默认构造方法，否则会报错
 * Created by hisenyuan on 2017/4/14 at 10:45.
 */
public class ReflectPersonDemo {

  @Test
  public void getOneMethod() {
    try {
      Class c = Class.forName("com.hisen.Reflect.get.Person");
      Object o = c.newInstance();
      Method method = c.getMethod("fun", String.class, int.class);
      method.invoke(o, "hisen", 33);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllMethod() {
    try {
      Class c = Class.forName("com.hisen.Reflect.get.Person");
      // 得到该类所有的方法，不包括父类的
      Method[] methods = c.getDeclaredMethods();
      // 得到该类所有的public方法，包括父类的
      Method[] methods1 = c.getMethods();
      for (Method m : methods) {
        String methodName = m.getName();
        System.out.println("methods(不包括父类):" + methodName);
      }
      System.out.println("---------------------------------");
      for (Method m : methods1) {
        String methodName = m.getName();
        System.out.println("methods(包括父类):" + methodName);
      }
      System.out.println("-----调用methods(不包括父类)数组中的方法-----");
      Object o = c.newInstance();
      //调用setName方法
      methods[1].invoke(o, "hisen");
      //调用getName方法
      System.out.println(methods[0].invoke(o));
      //调用fun无参数方法
      System.out.println(methods[2].invoke(o));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getOneDeclaredConstructor() {
    try {
      Class c = Class.forName("com.hisen.Reflect.get.Person");
      //获取构造函数
      Constructor constructor = c.getDeclaredConstructor(String.class);
      //设置是否允许访问，因为该构造器是private的，所以要手动设置允许访问，如果构造器是public的就不需要这行了。
      constructor.setAccessible(true);
      constructor.newInstance("hisen");
      //Class的newInstance方法，只能创建只包含无参数的构造函数的类，有参数用下面的
      //fromClass.getDeclaredConstructor(string.class).newInstance(“tengj”);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllDeclaredConstructor() {
    try {
      Class c = Class.forName("com.hisen.Reflect.get.Person");
      Constructor[] constructors = c.getDeclaredConstructors();
      for (Constructor s : constructors) {
        System.out.println(s.getName());
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
