package com.hisen.collection.list;

import java.util.LinkedList;
import java.util.List;

/**
 * LinkedList 的用法，以及使用LinkedList实现队列和栈
 */
public class LinkedListTest {

  public static void main(String[] args) {
    LinkedList<Integer> ll = new LinkedList<Integer>();
    ll.add(1);
    ll.add(2);
    ll.add(3);
    ll.add(4);
    ll.add(5);
    ll.add(6);
    //测试LinkedList的方法：getFirst、getLast
    System.out.println("第一个元素：" + ll.getFirst());
    System.out.println("第一个元素：" + ll.getLast());

    //遍历LinkedList中的元素
    for (Integer i : ll) {
      System.out.println(i);
    }

    //从LinkedList链表生成子链表
    List sub = ll.subList(1, 4);
    System.out.println("从链表生成子链表:" + sub);

    //LinkedList不指定索引，会被添加到链表尾部
    //可以作为队列或者堆栈使用
    ll.add(99);
    ll.addFirst(0);
    ll.addLast(99999999);
    System.out.println(ll);

    //使用链表实现栈效果
    System.out.println("---------------使用链表实现栈效果----------------");
    StackL stack = new StackL();
    for (int i = 0; i < 10; i++) {
      stack.push(i);
    }
    System.out.println("栈顶元素：" + stack.top());
    System.out.println("弹出元素：" + stack.pop());
    System.out.println("弹出元素：" + stack.pop());
    System.out.println("弹出元素：" + stack.pop());

    //使用链表实现队列效果
    System.out.println("---------------使用链表实现队列效果----------------");
    Queue queue = new Queue();
    for (int i = 0; i < 10; i++) {
      queue.put(Integer.toString(i));
    }
    while (!queue.isEmpty()) {
      System.out.println(queue.get());
    }
  }

  /**
   * 利用LinkedList定义栈
   *
   * @author hisenyuan 2017年1月14日    下午4:53:00
   */
  static class StackL {

    private LinkedList list = new LinkedList();

    public void push(Object v) {
      list.addFirst(v);
    }

    public Object top() {
      return list.getFirst();
    }

    public Object pop() {
      return list.removeFirst();
    }
  }

  /**
   * 利用LinkedList定义队列
   *
   * @author hisenyuan 2017年1月14日    下午4:55:52
   */
  static class Queue {

    private LinkedList list = new LinkedList();

    public void put(Object v) {
      list.addFirst(v);
    }

    public Object get() {
      return list.removeLast();
    }

    public Boolean isEmpty() {
      return list.isEmpty();
    }
  }
}
