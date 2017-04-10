package com.hisen.dto;

/**
 * Created by hisenyuan on 2017/4/6 at 17:19.
 */

import com.hisen.entity.Appointment;
import com.hisen.enums.AppointStateEnum;

/**
 * 封装预约执行后结果
 *
 * 一般我们使用DTO类来继承entity实体类，
 * 在DTO类里放一些业务字段，并提供get、set方法。
 * 当我们在业务逻辑层或者交互层用到一些数据库中不存在的字段时，
 * 我们就需要在DTO类里放这些字段，
 * 这些字段的意义就相当于一些经处理过的数据库字段，
 * 实质意义就是方便数据交互，提高效率。
 */
public class AppointExecution {

  // 图书ID
  private long bookId;

  // 秒杀预约结果状态
  private int state;

  // 状态标识
  private String stateInfo;

  // 预约成功对象
  private Appointment appointment;

  public AppointExecution() {
  }

  // 预约失败的构造器
  public AppointExecution(long bookId, AppointStateEnum stateEnum) {
    this.bookId = bookId;
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
  }

  // 预约成功的构造器
  public AppointExecution(long bookId, AppointStateEnum stateEnum, Appointment appointment) {
    this.bookId = bookId;
    this.state = stateEnum.getState();
    this.stateInfo = stateEnum.getStateInfo();
    this.appointment = appointment;
  }

  // 省略getter和setter方法，toString方法

  @Override
  public String toString() {
    return "AppointExecution{" +
        "bookId=" + bookId +
        ", state=" + state +
        ", stateInfo='" + stateInfo + '\'' +
        ", appointment=" + appointment +
        '}';
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getStateInfo() {
    return stateInfo;
  }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

  public Appointment getAppointment() {
    return appointment;
  }

  public void setAppointment(Appointment appointment) {
    this.appointment = appointment;
  }
}
