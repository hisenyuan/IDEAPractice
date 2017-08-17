package com.hisen.test.scriptengine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Java混合javascript脚本
 * 当某些代码需要经常改动时候，应当使用java+脚本
 * 因为这样不用编译，改了脚本之后不用重启java程序直接可以运行
 * Created by hisenyuan on 2017/8/17 at 10:02.
 */
public class ScriptTest {

  public static void main(String[] args)
      throws FileNotFoundException, ScriptException, NoSuchMethodException {
    // 获得一个javascript执行引擎
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
    // 建立上下文变量
    Bindings bindings = engine.createBindings();
    // 放入因子
    bindings.put("factor", 2);
    // 绑定上下文，作用域是当前引擎范围
    engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
    Scanner input = new Scanner(System.in);
    System.out.println("请输入两个参数，只能数字，输完一个按回车");
    // 在执行的过程中，可以修改js的函数。不影响执行，改完执行会得到修改后函数的期望值
    while (input.hasNext()) {
      int first = input.nextInt();
      int sec = input.nextInt();
      System.out.printf("输入的参数是：%s,%s", first, sec);
      // javascript脚本
      FileReader js = new FileReader("./src/main/java/com/hisen/test/scriptengine/model.js");
      // 执行js代码
      engine.eval(js);
      // 是否可以调用方法
      if (engine instanceof Invocable) {
        Invocable in = (Invocable) engine;
        // 执行js中的函数
        Double result = (Double) in.invokeFunction("formula", first, sec);
        System.out.println("运算结果：" + result);
      }
    }
  }
}
