package com.hisen.api.douban.controller;


import com.alibaba.fastjson.JSON;
import com.hisen.api.douban.bean.Book;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 利用豆瓣API获取图书信息
 * Created by hisen on 17-9-8.
 * E-mail: hisenyuan@gmail.com
 */
public class GetBookInfomationFromDouban {
  private static Logger logger = Logger.getLogger(GetBookInfomationFromDouban.class);

  @Test
  public void getBook(){
    String bookInformation = getBookInformation("26665630");
    logger.info("返回的json数据:"+bookInformation);
    Book book = JSON.parseObject(bookInformation, Book.class);
    logger.info("json数据转对象:"+JSON.toJSONString(book));
    logger.info("作者:"+book.getAuthor());
    logger.info("链接:"+book.getAlt());
  }
  /**
   * 根据id获取豆瓣信息
   * @param id
   * @return
   */
  public static String getBookInformation(String id) {
    logger.info("[开始] - [入参]" + id);
    String apiUrl = "https://api.douban.com/v2/book/" + id;
    logger.info("[请求地址]" + apiUrl);
    CloseableHttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(apiUrl);
    StringBuilder stringBuilder = new StringBuilder();
    try {
      CloseableHttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      String responseStr = EntityUtils.toString(entity, "utf-8");
      stringBuilder.append(responseStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.info("[结束] - [出参]" + stringBuilder.toString());
    return stringBuilder.toString();
  }
}
