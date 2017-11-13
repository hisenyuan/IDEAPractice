package com.hisen.interview.tiger20171110.btree;

/**
 * @author : yhx
 * @date : 2017/11/13 12:14
 * @descriptor : 对节点进行操作的接口
 */
public interface Visit {

  /**
   * 对节点进行某种操作
   * @param bTree 树的节点
   */
  public void visit(BTree bTree);
}
