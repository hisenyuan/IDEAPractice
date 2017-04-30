package com.hisen.ShowFileName;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ShowFileNameFrame extends JFrame implements ActionListener {

  public final int lable_width = 500;
  public final int lable_height = 350;
  // 获取屏幕的宽度
  public final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
  // 获取屏幕高度
  public final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
  JTextField t1 = new JTextField(20);
  JTextArea tt = new JTextArea(10, 10);
  JButton btn = new JButton("获取");
  JButton btn1 = new JButton("重置");
  JButton btn2 = new JButton("复制");

  public ShowFileNameFrame() {
    JPanel p1 = new JPanel();
    p1.add(new JLabel("文件路径:"));
    p1.add(t1);
    JPanel top = new JPanel(new GridLayout(1, 1));
    top.add(p1);
    JScrollPane center = new JScrollPane(tt);
    JPanel bottom = new JPanel();
    bottom.add(btn);
    bottom.add(btn2);
    bottom.add(btn1);
    this.getContentPane().add(top, "North");
    this.getContentPane().add(center, "Center");
    this.getContentPane().add(bottom, "South");
    btn.addActionListener(this);
    // 重置按钮,清空现有内容
    btn1.addActionListener(this);
    btn2.addActionListener(this);
    setTitle("获取文件名");
    // 显示在屏幕中心
    this.setLocation((width - lable_width) / 2, (height - lable_height) / 2);
    setSize(lable_width, lable_height);
    setVisible(true);
    // 点击关闭按钮，关闭系统
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    new ShowFileNameFrame();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btn) {
      String old = t1.getText();
      String delete = t1.getText() + "\\";
      if (old == null || old.equals("")) {
        JOptionPane.showMessageDialog(this, "输入为空,请重新输入");
      } else {
        // 遍历算法
        traverseFolder t = new traverseFolder();
        t.traverseFolder(old, delete, tt);
      }

    }
    if (e.getSource() == btn1) {
      t1.setText(null);
      tt.setText(null);
    }
    if (e.getSource() == btn2) {
      if (tt.getText() == null || "".equals(tt.getText())) {
        JOptionPane.showMessageDialog(this, "内容为空请先获取");
      } else {
        traverseFolder.setSystemClipboard(tt.getText());
      }
    }
  }

}
