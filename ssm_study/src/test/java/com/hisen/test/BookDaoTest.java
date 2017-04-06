package com.hisen.test;

import com.hisen.dao.BookDao;
import com.hisen.entity.Book;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisenyuan on 2017/4/6 at 17:03.
 */
public class BookDaoTest extends BaseTest {

  @Autowired
  private BookDao bookDao;

  @Test
  public void testQueryById() throws Exception {
    long bookId = 1000;
    Book book = bookDao.queryById(bookId);
    System.out.println(book);
  }

  @Test
  public void testQueryAll() throws Exception {
    List<Book> books = bookDao.queryAll(0, 5);
    for (Book book : books) {
      System.out.println(book);
    }
  }

  @Test
  public void testReduceNumber() throws Exception {
    long bookId = 1000;
    int update = bookDao.reduceNumber(bookId);
    System.out.println("update=" + update);
  }

}
