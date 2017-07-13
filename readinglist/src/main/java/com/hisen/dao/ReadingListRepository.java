package com.hisen.dao;

import com.hisen.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hisenyuan on 2017/7/13 at 20:17.
 */

/**
 * ReadingListRepository 直接继承了18个执行常用持久化操作的方法
 * JpaRepository 是一个泛型接口
 * 两个参数： 仓库操作的领域对象模型，及其ID熟悉的类型
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

  List<Book> findByReader(String reader);
}
