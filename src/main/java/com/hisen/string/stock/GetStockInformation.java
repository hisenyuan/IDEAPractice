package com.hisen.string.stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hisenyuan on 2017/4/27 at 10:44.
 */
public class GetStockInformation {

  public static void main(String[] args) {
    //股票代码数组
    String[] stocks = {"sh600877", "sz000616", "sh601669", "sh601216"};
    List<List<String[]>> lists = httpGet(stocks);
    System.out.println("map:-------------------------");
    for (List<String[]> arr : lists) {
      for (String[] strs : arr) {
        System.out.printf("%s ---> %s\n", strs[0], strs[1]);
      }
    }

  }

  public static List<List<String[]>> httpGet(String[] stocks) {
    String[] keys = {"股票名字", "今日开盘价", "昨日收盘价", "当前价格", "今日最高价", "今日最低价", "买一报价", "卖一报价", "成交数量(百股)",
        "成交金额(元)", "买一数量(股)", "买一报价", "买二数量(股)", "买二报价", "买三数量(股)", "买三报价", "买四数量(股)", "买四报价",
        "买五数量(股)", "买五报价", "卖一数量(股)", "卖一报价", "卖二数量(股)", "卖二报价", "卖三数量(股)", "卖三报价", "卖四数量(股)",
        "卖四报价", "卖五数量(股)", "卖五报价", "当前日期", "当前时间", "未知"};
    String s = Arrays.toString(stocks);
    String substring = s.substring(1, s.length() - 1).replace(" ", "");
    String strUrl = "http://hq.sinajs.cn/list=" + substring;
    System.out.println(strUrl);
    URL ur = null;
    BufferedReader reader = null;
    List<List<String[]>> strList = new ArrayList<>();
    try {
      ur = new URL(strUrl);
      ur.openConnection();
      reader = new BufferedReader(new InputStreamReader(ur.openStream(), "GBK"));
      String line;
      while ((line = reader.readLine()) != null) {
        String res = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
        System.out.printf("加工报文：%s\n", res);
        String[] split = res.split(",");
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
          String[] strs = new String[2];
          strs[0] = keys[i];
          strs[1] = split[i];
          list.add(strs);
        }
        strList.add(list);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return strList;
  }
}
