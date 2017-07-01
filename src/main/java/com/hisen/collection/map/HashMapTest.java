package com.hisen.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;

/**
 * Created by hisenyuan on 2017/7/1 at 22:36.
 * HashMap是基于哈希表
 * 允许使用null为key和null为value（与HashTable的大致区别在此）
 * 不保证映射的顺序，可能每次都会变动
 */
public class HashMapTest {

  @Test
  public void testHashMap() {
    Map<String, String> map = new HashMap<>();
    //插入数据
    for (int i = 0; i < 10; i++) {
      map.put(String.valueOf(i), "hisen" + i);
    }

    /**
     * 获取key的两种方式
     */
    //第一种获取的方式 效率比较高
    Iterator iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      String key = (String) entry.getKey();
      String value = (String) entry.getValue();
      System.out.printf("%s:%s\n", key, value);
    }
    //第二种获取的方式 效率比较低
    Iterator iterator1 = map.keySet().iterator();
    while (iterator1.hasNext()) {
      String key = (String) iterator1.next();
      String value = map.get(key);
      System.out.printf("%s:%s\n", key, value);
    }
    System.out.println("----------分隔符----------");
    /**
     * HashMap遍历的四种方式
     */
    //1. for each map.entrySet()
    for (Entry<String, String> entry : map.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      System.out.printf("第一种遍历方式：%s:%s\n", key, value);
    }
    //2. 显示调用map.entrySet()的集合迭代器
    Iterator<Entry<String, String>> iterator2 = map.entrySet().iterator();
    while (iterator2.hasNext()) {
      Entry<String, String> entry = (Entry<String, String>) iterator2.next();
      String key = entry.getKey();
      String value = entry.getValue();
      System.out.printf("第二种遍历方式：%s:%s\n", key, value);
    }
    //3.for each map.keySet()，再调用get获取
    for (String key : map.keySet()) {
      String value = map.get(key);
      System.out.printf("第三种遍历方式：%s:%s\n", key, value);
    }
    //4.for each map.entrySet()，用临时变量保存map.entrySet()
    Set<Entry<String, String>> entrySet = map.entrySet();
    for (Entry<String, String> entry : entrySet) {
      String key = entry.getKey();
      String value = entry.getValue();
      System.out.printf("第四种遍历方式：%s:%s\n", key, value);
    }

  }
}
