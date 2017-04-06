package com.hisen.service;

import com.hisen.dto.AppointExecution;
import com.hisen.entity.Book;
import java.util.List;

/**
 * Created by hisenyuan on 2017/4/6 at 17:22.
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface BookService {

  /**
   * 查询一本图书
   *
   * @param bookId
   * @return
   */
  Book getById(long bookId);

  /**
   * 查询所有图书
   *
   * @return
   */
  List<Book> getList();

  /**
   * 预约图书
   *
   * @param bookId
   * @param studentId
   * @return
   */
  AppointExecution appoint(long bookId, long studentId);

}
