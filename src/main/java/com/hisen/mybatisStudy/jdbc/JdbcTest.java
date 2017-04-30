package com.hisen.mybatisStudy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis学习第一节
 * Created by hisen on 17-3-24.
 */
public class JdbcTest {

  public static void main(String[] args) {
    //数据库链接
    Connection connection = null;
    //预编译的Statement，使用预编译的statement能提高数据库性能
    PreparedStatement preparedStatement = null;
    //结果集
    ResultSet resultSet = null;
    try {
      //加载数据库驱动
      Class.forName("com.mysql.jdbc.Driver");
      //获取数据库链接
      connection = DriverManager
          .getConnection("jdbc:mysql://127.0.0.1:3306/mvc?characterEncoding=utf-8", "root",
              "password");
      //定义sql语句， ？ 为占位符
      String sql = "select * from user where username = ?";
      //获取预处理Statement
      preparedStatement = connection.prepareCall(sql);
      //设置参数，1 为参数的序号，从1开始，后面为参数
      preparedStatement.setString(1, "王五");
      //查询出结果集
      resultSet = preparedStatement.executeQuery();
      //遍历结果集
      while (resultSet.next()) {
        System.out.println(resultSet.getString("id") + "  " + resultSet.getString("username"));
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {//释放各种资源
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
