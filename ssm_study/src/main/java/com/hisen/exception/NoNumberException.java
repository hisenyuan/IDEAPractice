package com.hisen.exception;

/**
 * Created by hisenyuan on 2017/4/6 at 17:20.
 * 库存不足异常
 */
public class NoNumberException extends RuntimeException {

  public NoNumberException(String message) {
    super(message);
  }

  public NoNumberException(String message, Throwable cause) {
    super(message, cause);
  }

}