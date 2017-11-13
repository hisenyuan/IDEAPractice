package com.hisen.interview.tiger20171110.btree;

import java.util.LinkedList;

/**
 * @author : yhx
 * @date : 2017/11/13 12:15
 * @descriptor :
 */
public class OrderBTree implements Visit {

  /**
   * 前序遍历
   *
   * @param root 根节点
   */
  public void preOrder(BTree root) {
    visit(root);
    if (root.getLfetTree() != null) {
      preOrder(root.getLfetTree());
    }
    if (root.getRightTree() != null) {
      preOrder(root.getRightTree());
    }
  }

  /**
   * 中序遍历
   *
   * @param root 根节点
   */
  public void inOrder(BTree root) {
    if (root.getLfetTree() != null) {
      inOrder(root.getLfetTree());
    }
    visit(root);
    if (root.getRightTree() != null) {
      inOrder(root.getRightTree());
    }
  }

  /**
   * 后序遍历
   * @param root 根节点
   */
  public void postOrder(BTree root) {

    if (root.getLfetTree() != null) {
      postOrder(root.getLfetTree());
    }
    if (root.getRightTree() != null) {
      postOrder(root.getRightTree());
    }
    visit(root);
  }

  /**
   * 层次遍历 - 利用队列
   * @param bTree 根节点
   */
  public void levelOrder(BTree bTree){
    if (bTree == null){
      return;
    }
    LinkedList<BTree> queue = new LinkedList<>();
    BTree current = null;
    // 将根节点入队列
    queue.offer(bTree);
    while (!queue.isEmpty()){
      // 队头元素出队列
      current = queue.poll();
      visit(current);
      // 如果左节点不为空，入队列
      if (current.getLfetTree() != null){
        queue.offer(current.getLfetTree());
      }
      // 如果右节点不为空，入队列
      if (current.getRightTree() != null){
        queue.offer(current.getRightTree());
      }
    }
  }
  /**
   * 访问二叉树的节点
   * @param bTree 树的节点
   */
  @Override
  public void visit(BTree bTree) {
    System.out.print(bTree.getRootData() + "\t");
  }
}
