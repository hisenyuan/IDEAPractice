package com.hisen.interview.tiger20171110.reverselinkedlist;

/**
 * @author : yhx
 * @date : 2017/11/13 15:08
 * @descriptor :
 */
public class ReverseNode {

  /**
   * 递归翻转单链表
   * @param head 前一结点
   * @return
   */
  public static Node reverseByRecursion(Node head){
    // 为空 或者 结尾
    if (head == null || head.getNext() == null) {
      return head;
    }
    // 先反转后续节点head.getNext()
    Node reHead = reverseByRecursion(head.getNext());
    // 将当前结点的指针域指向前一结点
    head.getNext().setNext(head);
    // 前一结点的指针域设置为null
    head.setNext(null);
    // 反转后新链表的头结点
    return reHead;
  }

  public static Node reverseByErgodic(Node head){
    if (head == null) {
      return head;
    }
    // 上一节点
    Node pre = head;
    // 当前节点
    Node cur = head.getNext();
    // 临时节点
    Node tmp;
    // 当前结点为null，说明位于尾结点
    while (cur!=null){
      // 存储下一个节点
      tmp = cur.getNext();
      // 设置当前节点的写一个节点为前一个节点
      cur.setNext(pre);
      // 指针往下移动
      pre = cur;
      cur = tmp;
    }
    // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
    head.setNext(null);
    return pre;
  }

  /**
   * 打印
   * @param node
   */
  public static void printNode(Node node){
    Node h = node;
    while (null != h) {
      System.out.print(h.getData() + " ");
      h = h.getNext();
    }
    System.out.println();
  }
}
