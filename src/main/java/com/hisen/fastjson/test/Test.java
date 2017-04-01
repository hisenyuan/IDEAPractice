package com.hisen.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hisen.fastjson.bean.Group;
import com.hisen.fastjson.bean.User;

/**
 * fastjson序列化和反序列化
 * Created by hisenyuan on 2017/3/22 at 17:51.
 */
public class Test {

  public static void main(String[] args) {
    //序列化
    encode();
    //反序列化
    decode();
  }

  /**
   * 序列化
   */
  public static void encode() {
    Group group = new Group();
    group.setId(0L);
    group.setName("admin");

    User guestUser = new User();
    guestUser.setId(2L);
    guestUser.setName("guest");

    User rootUser = new User();
    rootUser.setId(3L);
    rootUser.setName("root");

    group.addUser(guestUser);
    group.addUser(rootUser);

    String jsonString = JSON.toJSONString(group);
    System.out.println(jsonString);
  }

  /**
   * 反序列化
   */
  public static void decode() {
    String jsonString = "{\"id\":0,\"name\":\"admin\",\"users\":[{\"id\":2,\"name\":\"guest\"},{\"id\":3,\"name\":\"root\"}]}";
    Group group = JSON.parseObject(jsonString, Group.class);

    System.out.println(group.getId() + ":" + group.getName());
    for (User u : group.getUsers()) {
      System.out.println(u.getId() + ":" + u.getName());
    }
  }

  /**
   * 把字符串格式的json转换为json对象，取出数据
   */
  public static void string2Json() {
    String s = "{'A':'a'}";
    JSONObject obj = JSON.parseObject(s);
    System.out.println(obj.get("A"));
  }
}
