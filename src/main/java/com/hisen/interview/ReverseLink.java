package com.hisen.interview;

import org.junit.Test;

/**
 * @Author hisenyuan
 * @Description java$
 * @Date 2019-05-14 10:14
 */
public class ReverseLink {

    @Test
    public void testReverse() {
        LinkNode ln1 = new LinkNode(1);
        LinkNode ln2 = new LinkNode(2);
        LinkNode ln3 = new LinkNode(3);
        LinkNode ln4 = new LinkNode(4);
        LinkNode ln5 = new LinkNode(5);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;

        LinkNode linkNode = reverseLinkNode(ln1);
        printNode(linkNode);
    }

    // 打印链表
    private void printNode(LinkNode linkNode) {
        while (isOkNode(linkNode)) {
            System.out.println(linkNode.getData());
            linkNode = linkNode.next;
        }
    }

    // 判断node是否合法
    private boolean isOkNode(LinkNode linkNode) {
        return null != linkNode && linkNode.getData() != -1;
    }

    // 反转单链表
    private LinkNode reverseLinkNode(LinkNode head) {
        LinkNode first = head;
        LinkNode res = null;
        while (first != null) {
            LinkNode second = first.next;
            first.next = res;
            res = first;
            first = second;
        }
        return res;
    }

    // 单链表类
    class LinkNode {
        private int data;
        private LinkNode next;

        LinkNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public LinkNode getNext() {
            return next;
        }

        public void setNext(LinkNode next) {
            this.next = next;
        }
    }
}
