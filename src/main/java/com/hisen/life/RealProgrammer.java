package com.hisen.life;

/**
 * Created by hisenyuan on 2017/4/10 at 19:29.
 */
public class RealProgrammer {

  public static void main(String[] args) {

    System.out.println("去市场买一个西瓜，如果看到西红柿，买两个。");
    System.out.println("-------------------------");
    int tomatoe = 1;//1 代表市场上有西红柿
    if (tomatoe == 1) {
      System.out.println("看到西红柿");
      buyWatermelon(tomatoe);
    }
    System.out.println("-------------------------");
    tomatoe = 0;//0 代表市场上没有西红柿
    if (tomatoe == 0) {
      System.out.println("没看到西红柿");
      buyWatermelon(tomatoe);
    }
  }

  public static void buyWatermelon(int tomatoe) {
    int watermelon = 1;//买一个
    if (tomatoe != 0) {
      watermelon += 1;//如果看到西红柿，就买两个
    }
    System.out.println("购买西瓜的个数：" + watermelon);
  }
}
