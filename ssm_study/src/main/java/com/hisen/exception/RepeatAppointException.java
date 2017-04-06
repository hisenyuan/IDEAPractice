package com.hisen.exception;

/**
 * Created by hisenyuan on 2017/4/6 at 17:21.
 * 重复预约异常
 */
public class RepeatAppointException extends RuntimeException {

  public RepeatAppointException(String message) {
    super(message);
  }

  public RepeatAppointException(String message, Throwable cause) {
    super(message, cause);
  }

}
