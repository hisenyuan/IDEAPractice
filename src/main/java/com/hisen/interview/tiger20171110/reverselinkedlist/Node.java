package com.hisen.interview.tiger20171110.reverselinkedlist;

/**
 * @author : yhx
 * @date : 2017/11/13 15:02
 * @descriptor : 定义一个单链表
 */
public class Node {
  private int data;
  private Node next;

  public Node(int data) {
    this.data = data;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }
}
