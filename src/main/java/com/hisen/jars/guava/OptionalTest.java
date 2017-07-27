package com.hisen.jars.guava;

import com.google.common.base.Optional;

/**
 * Guava
 * Guava是一种基于开源的Java库，其中包含谷歌正在由他们很多项目使用的很多核心库。
 * 这个库是为了方便编码，并减少编码错误。
 * 这个库提供用于集合，缓存，支持原语，并发性，常见注解，字符串处理，I/O和验证的实用方法。
 *
 * Optional 避免null Created by hisenyuan on 2017/7/27 at 13:43.
 */
public class OptionalTest {

  public static void main(String[] args) {
    //创建指定引用的Optional实例，若引用为null则快速失败
    Optional<Integer> optional = Optional.of(5);
    //isPresent() 如果Optional包含非null的引用（引用存在），返回true
    boolean present = optional.isPresent();//true
    //get() 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
    Integer integer = optional.get();//5
    System.out.println(present + "\n" + integer);
  }
}
