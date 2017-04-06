package com.hisen.test;

import com.hisen.dao.AppointmentDao;
import com.hisen.entity.Appointment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisenyuan on 2017/4/6 at 17:17.
 */
public class AppointmentDaoTest extends BaseTest {

  @Autowired
  private AppointmentDao appointmentDao;

  @Test
  public void testInsertAppointment() throws Exception {
    long bookId = 1000;
    long studentId = 12345678910L;
    int insert = appointmentDao.insertAppointment(bookId, studentId);
    System.out.println("insert=" + insert);
  }

  @Test
  public void testQueryByKeyWithBook() throws Exception {
    long bookId = 1000;
    long studentId = 12345678910L;
    Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
    System.out.println(appointment);
    System.out.println(appointment.getBook());
  }

}
