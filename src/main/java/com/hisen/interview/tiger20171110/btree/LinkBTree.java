package com.hisen.interview.tiger20171110.btree;

/**
 * @author : yhx
 * @date : 2017/11/10 18:42
 * @descriptor : 二叉树的链表实现 - 二叉树的构造与遍历
 */
public class LinkBTree implements BTree {

  private Object data;
  private BTree lChild;
  private BTree rChild;

  public LinkBTree() {
    this.clearTree();
  }

  public LinkBTree(Object data) {
    this.data = data;
    this.rChild = null;
    this.lChild = null;
  }

  @Override
  public void addLfetTree(BTree lChild) {
    this.lChild = lChild;
  }

  @Override
  public BTree getLfetTree() {
    return lChild;
  }

  @Override
  public void addRightTree(BTree rChild) {
    this.rChild = rChild;
  }

  @Override
  public BTree getRightTree() {
    return rChild;
  }

  @Override
  public void clearTree() {
    this.data = null;
    this.rChild = null;
    this.lChild = null;
  }

  @Override
  public int getDeep() {
    return deep(this);
  }


  @Override
  public Object getRootData() {
    return data;
  }

  @Override
  public boolean hasLeftTree() {
    if (lChild != null) {
      return true;
    }
    return false;
  }

  @Override
  public boolean hasRightTree() {
    if (rChild != null) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isEmptyTree() {
    if ((lChild == null && rChild == null && data == null) || this == null) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isLeaf() {
    if (lChild == null && rChild == null) {
      return true;
    }
    return false;
  }

  @Override
  public void removeLeftTree() {
    lChild = null;
  }

  @Override
  public void removeRightTree() {
    rChild = null;
  }

  @Override
  public BTree getRoot() {
    return this;
  }

  @Override
  public void setRootData() {
    this.data = data;
  }

  @Override
  public int size() {
    return size(this);
  }

  private int size(BTree bTree) {
    if (bTree == null) {
      return 0;
    } else if (bTree.isLeaf()) {
      return 1;
    } else {
      if (bTree.getLfetTree() == null) {
        return size(bTree.getRightTree()) + 1;
      } else if (bTree.getRightTree() == null) {
        return size(bTree.getLfetTree()) + 1;
      } else {
        return size(bTree.getLfetTree()) + size(bTree.getRightTree()) + 1;
      }
    }
  }

  /**
   * 计算二叉树的高度
   */
  private int deep(BTree bTree) {
    if (bTree.isEmptyTree()) {
      return 0;
    } else if (bTree.isLeaf()) {
      return 1;
    } else {
      if (bTree.getLfetTree() == null) {
        return deep(bTree.getRightTree()) + 1;
      } else if (bTree.getRightTree() == null) {
        return deep(bTree.getLfetTree()) + 1;
      } else {
        return Math.max(deep(bTree.getLfetTree()), deep(bTree.getRightTree())) + 1;
      }
    }
  }
}
