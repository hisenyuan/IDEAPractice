package com.hisen.dao;

import com.hisen.entity.Appointment;
import org.apache.ibatis.annotations.Param;

/**
 * Created by hisenyuan on 2017/4/6 at 16:59.
 */
public interface AppointmentDao {

  /**
   * 插入预约图书记录
   *
   * @param bookId
   * @param studentId
   * @return 插入的行数
   */
  int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);

  /**
   * 通过主键查询预约图书记录，并且携带图书实体
   *
   * @param bookId
   * @param studentId
   * @return
   */
  Appointment queryByKeyWithBook(@Param("bookId") long bookId, @Param("studentId") long studentId);

}
