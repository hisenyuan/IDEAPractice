package com.hisen.programLogicOfThinking;

/**
 * 介绍条件，循环，函数，及其原理
 * Created by hisen on 17-8-14.
 * E-mail: hisenyuan@gmail.com
 */
public class Part4 {

  public static void main(String[] args) {
    /**
     * for循环详解
     *
     * for(初始化语句; 循环条件; 步进操作){
     * 循环体
     * }
     *
     * 1.执行初始化指令
     * 2.检查循环条件是否为true，如果为false，跳转到第6步
     * 3.循环条件为真，执行循环体
     * 4.执行步进操作
     * 5.步进操作执行完后，跳转到第2步，即继续检查循环条件。
     * 6.for循环后面的语句
     */
    int[] arr = {1,2,3,4};
    for(int i=0;i<arr.length;i++){
      System.out.println(arr[i]);
    }

    /**
     * 栈
     * 栈底的内存地址是最高的，栈顶的是最小的。
     * 计算机系统主要使用栈来存放函数调用过程中需要的数据，包括参数、返回地址，函数内定义的局部变量也放在栈中
     * 函数中的参数和函数内定义的变量，都分配在栈中，在函数被调用的时候才分配，调用结束后释放。
     *
     * 数组和对象
     * 一块存放实际的内容 - 堆
     * 一块存放实际内容的地址 - 栈
     *
     * 栈空间没有变量指向它的时候，Java系统会自动进行垃圾回收，从而释放这块空间。
     *
     * 递归
     * 每递归调用一次，栈的深度就增加一层（存放执行过程）
     *
     * 函数调用的成本
     * 每一次调用都需要分配额外的栈空间用于存储参数、局部变量以及返回地址，需要进行额外的入栈和出栈操作。
     */

    /**
     * 堆：两个值，一个new出来的对象，一个"hisen"字符串
     * 栈：str
     */
    String str = new String("hisen");
    int a = 10000;
    replaceA(str);
    /**
     * 小结
     * 函数调用主要是通过栈来存储相关数据的，
     * 系统就函数调用者和函数如何使用栈做了约定，
     * 返回值我们简化认为是通过一个专门的返回值存储器存储的
     */
  }

  /**
   * 递归函数
   *
   * StackOverflowError （栈内存不够，死循环）
   */
  public static void replaceA(String s){
    replaceA(s.replace(s.substring(1),"h"));
    System.out.println(s);
  }
}
