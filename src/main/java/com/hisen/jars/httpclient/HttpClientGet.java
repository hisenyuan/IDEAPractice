package com.hisen.jars.httpclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HttpClientGet支持Https请求
 * Created by hisenyuan on 2017/7/3 at 22:05.
 */
public class HttpClientGet {

  private static Logger logger = Logger.getLogger(HttpClientGet.class);

  public static void main(String[] args) {
    String url = "http://hq.sinajs.cn/list=";
    //股票代码数组
    List<String> list = new ArrayList<>();
    list.add("sh600877");
    list.add("sz000616");
    list.add("sh601669");
    list.add("sh601216");
    String stackInfo = getStackInfo(url, list);
    List<List<String>> lists = analyzeStackInfo(stackInfo);
    for (List<String> infos : lists){
      System.out.println(infos.get(0)+":"+infos.get(1));
    }
  }

  /**
   * 获取返回的字符串数据
   * @param url 请求地址
   * @param list 参数列表
   * @return
   */
  public static String getStackInfo(String url,List<String> list) {
    String params = String.join(",",list);;
    logger.info(" >>>>> http请求地址 <<<<<："+url + params);
    CloseableHttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(url + params);
    StringBuilder stringBuilder = new StringBuilder();
    try {
      CloseableHttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      String responseStr = EntityUtils.toString(entity, "utf-8");
      stringBuilder.append(responseStr);
      logger.info(" >>>>> 返回数据 <<<<<：\n"+stringBuilder.toString());
    } catch (Exception e) {
      logger.info(" >>>>> 查询出错 <<<<<"+url + e.getMessage() + e);
      e.printStackTrace();
    }finally {
      if (stringBuilder.length()<0){
        stringBuilder.append("未知错误");
      }
      return stringBuilder.toString();
    }
  }

  /**
   * 分析股票数据
   * @param stackInfo
   * @return
   */
  public static List<List<String>> analyzeStackInfo(String stackInfo){
    String[] keys = {"股票名字", "今日开盘价", "昨日收盘价", "当前价格", "今日最高价", "今日最低价", "买一报价", "卖一报价", "成交数量(百股)",
        "成交金额(元)", "买一数量(股)", "买一报价", "买二数量(股)", "买二报价", "买三数量(股)", "买三报价", "买四数量(股)", "买四报价",
        "买五数量(股)", "买五报价", "卖一数量(股)", "卖一报价", "卖二数量(股)", "卖二报价", "卖三数量(股)", "卖三报价", "卖四数量(股)",
        "卖四报价", "卖五数量(股)", "卖五报价", "当前日期", "当前时间", "未知"};
    List<String> list = Arrays.asList(stackInfo.split(";"));
    List<List<String>> amountList = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      Pattern pattern = Pattern.compile("\"([^\"]*)\"");// 匹配的模式
      Matcher m = pattern.matcher(list.get(i));
      while (m.find()){
        String[] split = m.group(0).substring(1,m.group(0).length()-1).split(",");
        for (int j = 0; j < split.length; j++) {
          List<String> infos = new ArrayList<>();
          infos.add(keys[j]);
          infos.add(split[j]);
          amountList.add(infos);
        }
      }
    }
    return amountList;
  }
}
