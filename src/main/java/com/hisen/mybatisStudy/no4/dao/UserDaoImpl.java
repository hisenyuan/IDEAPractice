package com.hisen.mybatisStudy.no4.dao;

import com.hisen.mybatisStudy.po.User;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by hisen on 17-3-25.
 */
public class UserDaoImpl implements UserDao {

  // 需要向dao实现类中注入SqlSessionFactory
  // 这里通过构造方法注入
  private SqlSessionFactory sqlSessionFactory;

  public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public User findUserById(int id) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    User user = sqlSession.selectOne("Get_GCD_LCM.findUserById", id);
    //释放资源
    sqlSession.close();
    return user;
  }

  @Override
  public List<User> findUserByName(String name) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    List<User> list = sqlSession.selectList("Get_GCD_LCM.findUserByName", name);
    // 释放资源
    sqlSession.close();
    return list;
  }

  @Override
  public void insertUser(User user) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //执行插入操作
    sqlSession.insert("Get_GCD_LCM.insertUser", user);
    // 提交事务
    sqlSession.commit();
    // 释放资源
    sqlSession.close();
  }

  @Override
  public void deleteUser(int id) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //执行插入操作
    sqlSession.delete("Get_GCD_LCM.deleteUser", id);
    // 提交事务
    sqlSession.commit();
    // 释放资源
    sqlSession.close();
  }
}
