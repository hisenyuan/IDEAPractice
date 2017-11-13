package com.hisen.interview.tiger20171110.btree;

/**
 * @author : yhx
 * @date : 2017/11/10 18:26
 * @descriptor : 二叉树接口
 */
public interface BTree {

  /**
   * 添加左子树
   *
   * @param lChild 左子树
   */
  public void addLfetTree(BTree lChild);

  /**
   * 获取左孩子节点
   */
  public BTree getLfetTree();

  /**
   * 添加右子树
   *
   * @param rChild 右子树
   */
  public void addRightTree(BTree rChild);

  /**
   * 获取右孩子节点
   */
  public BTree getRightTree();

  /**
   * 置空树
   */
  public void clearTree();

  /**
   * 获取树的深度
   */
  public int getDeep();

  /**
   * 获取根节点
   */
  public Object getRootData();

  /**
   * 判断是否有左子树
   */
  public boolean hasLeftTree();

  /**
   * 判断是否有右子树
   */
  public boolean hasRightTree();

  /**
   * 判断是否为空树
   *
   * @return 如果为空，返回true,否则返回false
   */
  public boolean isEmptyTree();

  /**
   * 判断是否为叶子节点
   * @return
   */
  public boolean isLeaf();

  /**
   * 删除左子树
   */
  public void removeLeftTree();

  /**
   * 删除右子树
   */
  public void removeRightTree();

  /**
   * 获得树根
   * @return 树的跟
   */
  public BTree getRoot();

  /**
   * 设置根节点的数据
   */
  public void setRootData();

  /**
   * 求节点的个数
   * @return 节点的个数
   */
  public int size();
}
