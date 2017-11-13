package com.hisen.interview.tiger20171110.btree;

/**
 * @author : yhx
 * @date : 2017/11/13 12:21
 * @descriptor : 测试 - 二叉树的构造与遍历
 */
public class BTreeMain {

  public static void main(String[] args) {
    /**
     * 构造一棵树
     *     A
     *   B    C
     * D   E    F
     */
    BTree bTree = new LinkBTree("A");
    BTree bt1, bt2, bt3, bt4, bt5;
    bt1 = new LinkBTree("B");
    bTree.addLfetTree(bt1);

    bt2 = new LinkBTree("D");
    bt1.addLfetTree(bt2);

    bt3 = new LinkBTree("C");
    bTree.addRightTree(bt3);

    bt4 = new LinkBTree("E");
    bt3.addLfetTree(bt4);

    bt5 = new LinkBTree("F");
    bt3.addRightTree(bt5);

    System.out.println("树的深度：" + bTree.getDeep());
    System.out.println("树的节点：" + bTree.size());
    System.out.println("是否为空树：" + bTree.isEmptyTree());
    System.out.println("是否为叶子节点：" + bTree.isLeaf());
    System.out.println("最右下边是否为叶子节点：" + bTree.getRightTree().getRightTree().isLeaf());
    System.out.println("root节点：" + bTree.getRootData());

    OrderBTree orderBTree = new OrderBTree();
    System.out.println("\n前序遍历结果");
    orderBTree.preOrder(bTree);
    System.out.println("\n中序遍历结果");
    orderBTree.inOrder(bTree);
    System.out.println("\n后序遍历结果");
    orderBTree.postOrder(bTree);
    System.out.println("\n层次遍历结果");
    orderBTree.levelOrder(bTree);
    System.out.println("\n访问右下方节点数据");
    orderBTree.visit(bTree.getRightTree().getRightTree());

    bTree.removeLeftTree();
    System.out.println("\n删除左子树后中序遍历为：");
    orderBTree.inOrder(bTree);
  }
}
