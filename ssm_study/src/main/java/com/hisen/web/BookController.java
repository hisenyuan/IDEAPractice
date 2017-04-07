package com.hisen.web;

import com.hisen.dto.AppointExecution;
import com.hisen.dto.Result;
import com.hisen.entity.Book;
import com.hisen.enums.AppointStateEnum;
import com.hisen.exception.NoNumberException;
import com.hisen.exception.RepeatAppointException;
import com.hisen.service.BookService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hisenyuan on 2017/4/6 at 17:27.
 * 在項目終端Terminal可以測試：curl -H "Accept:application/json; charset=utf-8" -D "studentId=1234567890" 127.0.0.1:8848/ssm/book/1003/appoint
 */
@Controller
@RequestMapping("/book") // url:/模块/资源/{id}/细分 /seckill/list
public class BookController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private BookService bookService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  private String list(Model model) {
    List<Book> list = bookService.getList();
    model.addAttribute("list", list);
    // list.jsp + model = ModelAndView
    return "list";// WEB-INF/jsp/"list".jsp
  }

  @RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
  private String detail(@PathVariable("bookId") Long bookId, Model model) {
    if (bookId == null) {
      return "redirect:/book/list";
    }
    Book book = bookService.getById(bookId);
    if (book == null) {
      return "forward:/book/list";
    }
    model.addAttribute("book", book);
    return "detail";
  }

  //ajax json
  @RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {
      "application/json; charset=utf-8" })
  @ResponseBody
  private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @RequestParam("studentId") Long studentId) {
    if (studentId == null || studentId.equals("")) {
      return new Result<>(false, "学号不能为空");
    }
    //AppointExecution execution = bookService.appoint(bookId, studentId);//错误写法，不能统一返回，要处理异常（失败）情况
    AppointExecution execution = null;
    try {
      execution = bookService.appoint(bookId, studentId);
    } catch (NoNumberException e1) {
      execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
    } catch (RepeatAppointException e2) {
      execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
    } catch (Exception e) {
      execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
    }
    return new Result<AppointExecution>(true, execution);
  }

  //加上这个解决乱码问题
  // 当返回为字符串的时候：produces = "text/plain;charset=UTF-8"
  // 当返回为json的时候：produces = "application/json; charset=utf-8"
  @RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
  @ResponseBody
  private String add(Book book) {
    String s = book.toString();
    System.out.println(s);
    Book hasBook = bookService.getById(book.getBookId());
    int i = -2;
    if (hasBook == null) {
      i = bookService.addBook(book);
    }
    return i>0?"success":"error";
  }

}
