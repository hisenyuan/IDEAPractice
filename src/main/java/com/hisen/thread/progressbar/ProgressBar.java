package com.hisen.thread.progressbar;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 * 显示一个进度条
 *
 * @author hisenyuan 2017年1月14日    下午5:40:14
 */
public class ProgressBar extends JWindow implements Runnable {

  public final int lable_width = 350;
  public final int lable_height = 250;
  //获取屏幕的宽度
  public final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
  //获取屏幕高度
  public final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
  public JLabel lable;
  public JProgressBar progressBar;

  /**
   * 无参构造方法
   */
  public ProgressBar() {
    //设置要显示的图片
    lable = new JLabel(new ImageIcon("src/com/hisen/thread/progressbar/1.jpg"));
    //指定lable大小和位置
    lable.setBounds(0, 0, lable_width, lable_height);
    progressBar = new JProgressBar();
    //进度条前景色
    progressBar.setForeground(new Color(123, 123, 123));
    //背景色
    progressBar.setBackground(new Color(0, 0, 0));
    progressBar.setStringPainted(true);
    progressBar.setBorderPainted(true);
    progressBar.setBounds(0, lable_height - 15, lable_width, 15);
    this.add(lable);
    this.add(progressBar);

    this.setLayout(null);
    //显示在屏幕中心
    this.setLocation((width - lable_width) / 2, (height - lable_height) / 2);
    this.setVisible(true);
    this.setSize(lable_width, lable_height);

  }

  public static void main(String[] args) {
    //创建线程并启动
    new Thread(new ProgressBar()).start();
  }

  /**
   * 重写run方法
   */
  @Override
  public void run() {
    for (int i = 0; i < 100; i += 2) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      progressBar.setValue(i);
    }
    JOptionPane.showMessageDialog(this, "加载完成，请退出");
    //释放屏幕资源
    this.dispose();
  }

}
