package com.hisen.lambda;

/**
 * 参考：http://www.jianshu.com/p/e7db8fddb8b4
 * 编译器通过以下上下文识别Lambda表达式类型：
 * 变量声明
 * 赋值
 * return语句
 * 数组初始化
 * 方法或构造方法参数
 * Lambda表达式体
 * 条件语句?:
 * 类型转换
 * Created by hisenyuan on 2017/8/3 at 11:23.
 */
public class ContextTest {

  static void invoke(Runnable r) {
    r.run();
  }

  static <T> T invoke(Callable<T> c) throws Exception {
    return c.call();
  }

  public interface Runnable {

    void run();
  }

  public interface Callable<V> {

    V call();
  }

  public static void main(String[] args) throws Exception {
    String s = invoke(() -> "done");
//    最终会调用invoke(Callable)，因为Runnable.run没有返回值。所以表达式() -> "done"的类型为Callable<T>。
  }

}
