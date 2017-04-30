package com.hisen.tieba;

import java.util.ArrayList;
import java.util.List;

public class BaiduPostBar1 {

  //定义泛型集合
  public static List<BaiduPostBar> barList = new ArrayList<BaiduPostBar>();

  //查询主帖
  public void selectBar() {
    for (int i = 0; i < barList.size(); i++) {
      System.out.println(barList.get(i));
    }
  }
}
