package com.hisen.test;

import com.hisen.dto.AppointExecution;
import com.hisen.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisenyuan on 2017/4/6 at 17:25.
 */
public class BookServiceImplTest extends BaseTest {

  @Autowired
  private BookService bookService;

  @Test
  public void testAppoint() throws Exception {
    long bookId = 1001;
    long studentId = 12345678910L;
    AppointExecution execution = bookService.appoint(bookId, studentId);
    System.out.println(execution);
  }

}
