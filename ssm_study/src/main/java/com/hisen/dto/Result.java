package com.hisen.dto;

/**
 * Created by hisenyuan on 2017/4/6 at 17:26.
 * 封装json对象，所有返回结果都使用它
 *
 * 一般我们使用DTO类来继承entity实体类，
 * 在DTO类里放一些业务字段，并提供get、set方法。
 * 当我们在业务逻辑层或者交互层用到一些数据库中不存在的字段时，
 * 我们就需要在DTO类里放这些字段，
 * 这些字段的意义就相当于一些经处理过的数据库字段，
 * 实质意义就是方便数据交互，提高效率。
 */
public class Result<T> {

  private boolean success;// 是否成功标志

  private T data;// 成功时返回的数据

  private String error;// 错误信息

  public Result() {
  }

  // 成功时的构造器
  public Result(boolean success, T data) {
    this.success = success;
    this.data = data;
  }

  // 错误时的构造器
  public Result(boolean success, String error) {
    this.success = success;
    this.error = error;
  }

  // 省略getter和setter方法

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}