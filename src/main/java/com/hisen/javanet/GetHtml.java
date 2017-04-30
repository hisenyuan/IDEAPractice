package com.hisen.javanet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java获取网页源代码
 *
 * @author hisenyuan 2016年4月7日    下午5:06:12
 */
public class GetHtml {

  public static void main(String[] args) throws IOException {
    //首页链接
    String lianjie = "http://yhx.wiki";
    //获取首页内容
    String data = getContext(lianjie);
    //批量获取日志链接
    ArrayList<String> al = getUrl(data);
    //存标题
    ArrayList<String> t = new ArrayList<String>();
    for (String s : al) {
      //获取日志内容
      String logdata = getContext(s);
      //获取标题，标题替换效率很低
      t.add(getTitle(logdata).replace("<title>", "").replace("</title>", ""));
    }
    for (String s : t) {
      System.out.println(s);
    }
  }

  /**
   * 获取网页内容
   *
   * @param lianjie 传入网页链接
   */
  public static String getContext(String lianjie) throws IOException {
    URL url = new URL(lianjie);
    //获得HTTP连接
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //通过输入流获取网站数据
    InputStream inputStream = conn.getInputStream();
    //readInputStream(把inputStream流转换为byte数组)方法在下面定义了
    byte[] getData = readInputStream(inputStream);
    //把byte数组转换为string字符串
    String data = new String(getData, "UTF-8");
    return data;
  }

  /**
   * 把InputStream中的内容转换为二进制数组
   *
   * @param inputStream 已经加载了网页内容的输入
   * @return 返回网页内容
   */
  private static byte[] readInputStream(InputStream inputStream) throws IOException {
    byte[] buffer = new byte[1024];
    int len = 0;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    while ((len = inputStream.read(buffer)) != -1) {
      bos.write(buffer, 0, len);
    }
    bos.close();
    return bos.toByteArray();
  }

  /**
   * 获取页面所有的日志链接
   */
  public static ArrayList<String> getUrl(String data) {
    ArrayList<String> al = new ArrayList<String>();
    String pattern = "[a-z]{4}[^a-z0-9A-Z]{3}[a-z]{3}[^a-z0-9A-Z][a-z]{4}[^a-z0-9A-Z][\\d]{4}[^a-z0-9A-Z][\\d]{2}[^a-z0-9A-Z][\\d]{2}[^a-z0-9A-Z][a-z]{2}[^a-z0-9A-Z][\\d]{1,10}[^a-z0-9A-Z][a-z]{4}";
    // 创建 Pattern 对象
    Pattern r = Pattern.compile(pattern);
    // 现在创建 matcher 对象
    Matcher m = r.matcher(data);
    //循环获取目标链接
    while (m.find()) {
      //把获取到的链接装进去
      al.add(m.group(0));
    }
    return al;
  }

  /**
   * 获取网页标题
   */
  public static String getTitle(String data) {
    String title = null;
    String pattern = "<title>(.*)</title>";
    // 创建 Pattern 对象
    Pattern r = Pattern.compile(pattern);
    // 现在创建 matcher 对象
    Matcher m = r.matcher(data);
    //循环获取目标链接
    while (m.find()) {
      //把获取到的链接装进去
      title = (m.group(0));
    }
    return title;
  }
}
