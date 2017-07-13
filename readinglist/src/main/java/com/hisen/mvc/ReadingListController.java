package com.hisen.mvc;

import com.hisen.dao.ReadingListRepository;
import com.hisen.entity.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hisenyuan on 2017/7/13 at 20:21.
 * Controller 添加这个注解之后,组件扫描会将其注册为Spring应用程序上下文里的一个Bean
 */
@Controller
@RequestMapping("/readingList")
public class ReadingListController {

  private ReadingListRepository readingListRepository;

  @Autowired
  public ReadingListController(ReadingListRepository readingListRepository) {
    this.readingListRepository = readingListRepository;
  }

  @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
  public String readerBooks(@PathVariable("reader") String reader, Model model) {
    List<Book> readingList = readingListRepository.findByReader(reader);
    if (null != readingList) {
      model.addAttribute("books", readingList);
    }
    return "readingList";
  }

  @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
  public String addToReadingList(@PathVariable("reader") String reader, Book book) {
    readingListRepository.save(book);
    return "redirect:/readingList/{reader}";
  }
}
