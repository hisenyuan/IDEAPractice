package com.hisen.jars.jmx;

/**
 * 接口应该严格按照类名+MBean
 * Created by hisenyuan on 2017/5/15 at 20:03.
 */
public interface HelloMBean {

  public String getName();

  public void setName(String name);

  public String getAge();

  public void setAge(String age);

  public void helloWorld();

  public void helloWorld(String str);

  public void getTelephone();
}
