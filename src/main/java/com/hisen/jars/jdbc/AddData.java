package com.hisen.jars.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

/**
 * Created by hisenyuan on 2017/5/16 at 16:27.
 */
public class AddData {

  private static String url = "jdbc:mysql://127.0.0.1:3306/hisen?user=root&password=hisen&useUnicode=true&characterEncoding=UTF8";
  ;

  @Test
  public void addData() {
    Connection connect = DBUtils.getConnect(url);
    String[] arr = {"标题", "内容"};
    for (int i = 0; i < 10000; i++) {
      for (int j = 0; j < 20; j++) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into post(title,content) values('");
        sb.append(arr[0] + j).append("','").append(arr[1] + j).append("');");
        System.out.println(sb.toString());
        try {
          Statement statement = connect.createStatement();
          int i1 = statement.executeUpdate(sb.toString());
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
