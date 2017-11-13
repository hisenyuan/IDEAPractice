package com.hisen.interview.tiger20171110.reverselinkedlist;

/**
 * @author : yhx
 * @date : 2017/11/13 15:19
 * @descriptor :
 */
public class ReverseNodeMain {

  public static void main(String[] args) {
    /*********************  测试递归翻转 *********************/
    // 前一个节点
    Node head = new Node(0);
    // 第一个节点
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);

    head.setNext(node1);
    node1.setNext(node2);
    node2.setNext(node3);

    ReverseNode.printNode(head);
    System.out.println("递归翻转");
    ReverseNode.printNode(ReverseNode.reverseByRecursion(head));

    /*********************  测试遍历翻转 *********************/
    // 前一个节点
    Node head1 = new Node(0);
    // 第一个节点
    Node node11 = new Node(1);
    Node node21 = new Node(2);
    Node node31 = new Node(3);

    head1.setNext(node11);
    node11.setNext(node21);
    node21.setNext(node31);
    ReverseNode.printNode(head);
    System.out.println("遍历翻转");
    ReverseNode.printNode(ReverseNode.reverseByErgodic(head1));
  }
}
