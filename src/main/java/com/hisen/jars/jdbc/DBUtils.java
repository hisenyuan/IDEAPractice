package com.hisen.jars.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by hisenyuan on 2017/5/16 at 15:55.
 */
public class DBUtils {

  /**
   * 获得连接
   * @param URL
   */
  public static Connection getConnect(String URL) {
    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("mysql has connected！");
      connection = DriverManager.getConnection(URL);
      return connection;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println(e);
    } catch (SQLException e) {
      System.out.println(e);
      e.printStackTrace();
    }
    return connection;
  }
}
