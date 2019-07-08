package com.hisen.github.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Created by hisenyuan on 2017/4/28 at 10:21.
 */
public class UserInformation {

  public static void main(String[] args) {
    String user = "hisenyuan";
    String userUrl = "https://api.github.com/users/" + user;
    String reposUrl = "https://api.github.com/users/" + user + "/repos";

    String githubReposInformation = getGithubAPIJson(reposUrl);
    JSONArray jsonArray = JSON.parseArray(githubReposInformation);
    /*
    System.out.println("fastjson无序遍历结果：");
    for (Object jsonObj:jsonArray){
      for (Map.Entry<string, Object> entry : JSON.parseObject(jsonObj.toString()).entrySet()) {
        System.out.println(entry.getKey() + ":" + entry.getValue());
      }
    }
    System.out.println("-------------------");
    for (Object jsonObj:jsonArray){
      System.out.println("fastjson有序遍历结果：");
      LinkedHashMap<string, string> jsonMap = JSON.parseObject(jsonObj.toString(),new TypeReference<LinkedHashMap<string, string>>() {});
      for (Map.Entry<string, string> entry : jsonMap.entrySet()) {
        System.out.println(entry.getKey() + ":" + entry.getValue());
      }
    }
    */
    for (Object jsonObj : jsonArray) {
      System.out.println("---------解析后的项目信息---------");
      LinkedHashMap<String, String> jsonMap = JSON
          .parseObject(jsonObj.toString(), new TypeReference<LinkedHashMap<String, String>>() {
          });
      String id = "项目编号：" + jsonMap.get("id");
      String name = "项目名称：" + jsonMap.get("name");
      String html_url = "项目地址：" + jsonMap.get("html_url");
      String created_at = "创建时间：" + jsonMap.get("created_at").replace("T", " ").replace("Z", "");
      String updated_at = "更新时间：" + jsonMap.get("updated_at").replace("T", " ").replace("Z", "");
      String pushed_at = "提交时间：" + jsonMap.get("pushed_at").replace("T", " ").replace("Z", "");
      String size = "项目大小：" + jsonMap.get("size");
      String language = "编程语言：" + jsonMap.get("language");
      System.out
          .printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", id, name, html_url, created_at, updated_at,
              pushed_at, size, language);
    }
    String githubUerInformation = getGithubAPIJson(userUrl);

    /*
    System.out.println("fastjson无序遍历结果：");
    JSONObject jsonObj = JSON.parseObject(githubUerInformation);
    for (Map.Entry<string, Object> entry : jsonObj.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }
    System.out.println("-------------------");
    System.out.println("fastjson有序遍历结果：");
    LinkedHashMap<string, string> jsonMap = JSON
        .parseObject(githubUerInformation, new TypeReference<LinkedHashMap<string, string>>() {
        });
    for (Map.Entry<string, string> entry : jsonMap.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }
    */
    LinkedHashMap<String, String> jsonMap = JSON
        .parseObject(githubUerInformation, new TypeReference<LinkedHashMap<String, String>>() {
        });
    System.out.println("---------解析后的个人信息信息----------");
    String login = "登录名称：" + jsonMap.get("login");
    String id = "数字编号：" + jsonMap.get("id");
    String avatar_url = "头像地址：" + jsonMap.get("avatar_url");
    String name = "用户昵称：" + jsonMap.get("name");
    String blog = "博客地址：" + jsonMap.get("blog");
    String location = "地理位置：" + jsonMap.get("location");
    String email = "个人邮箱：" +  jsonMap.get("email");
    String bio = "个人说明：" + jsonMap.get("bio");
    String public_repos = "仓库个数：" + jsonMap.get("public_repos");
    String created_at = "创建时间：" + jsonMap.get("created_at").replace("T", " ").replace("Z", "");
    String updated_at = "最后更新：" + jsonMap.get("updated_at").replace("T", " ").replace("Z", "");
    System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", login, id, avatar_url, name, blog,
        location,email, bio, public_repos, created_at, updated_at);
  }

  public static String getGithubAPIJson(String url) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      URL githubapi = new URL(url);
      BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(githubapi.openStream(), "UTF-8"));
      String line = null;
      while (null != (line = bufferedReader.readLine())) {
        stringBuilder.append(line);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }
}
