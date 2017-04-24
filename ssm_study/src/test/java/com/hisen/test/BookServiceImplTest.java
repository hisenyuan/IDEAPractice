package com.hisen.test;

import com.hisen.dto.AppointExecution;
import com.hisen.entity.Book;
import com.hisen.service.BookService;
import java.util.Random;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisenyuan on 2017/4/6 at 17:25.
 */
public class BookServiceImplTest extends BaseTest {

  @Autowired
  private BookService bookService;

  /**
   * 测试图书预约功能
   * @throws Exception
   */
  @Test
  public void testAppoint() throws Exception {
    long bookId = 1001;
    long studentId = 12345678910L;
    AppointExecution execution = bookService.appoint(bookId, studentId);
    System.out.println(execution);
  }

  /**
   * 批量添加图书数据
   */
  @Test
  public void testAdd(){
    for (int i = 1; i < 100; i++) {
      Book book = new Book();
      book.setBookId(i);
      book.setName(String.valueOf((char) i+20048));
      book.setNumber(i*10);
      bookService.addBook(book);
    }
  }

}
