package com.hisen.exception;

/**
 * Created by hisenyuan on 2017/4/6 at 17:21.
 * 预约业务异常
 */
public class AppointException extends RuntimeException {

  public AppointException(String message) {
    super(message);
  }

  public AppointException(String message, Throwable cause) {
    super(message, cause);
  }

}
