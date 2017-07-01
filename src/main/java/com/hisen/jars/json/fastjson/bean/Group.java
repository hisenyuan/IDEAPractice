package com.hisen.jars.json.fastjson.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hisenyuan on 2017/3/22 at 17:48.
 */
public class Group {

  private Long id;
  private String name;
  private List<User> users = new ArrayList<User>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public void addUser(User user) {
    users.add(user);
  }
}
