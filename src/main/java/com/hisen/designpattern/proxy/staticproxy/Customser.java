package com.hisen.designpattern.proxy.staticproxy;

/**
 * @author : yhx
 * @date : 2017/11/9 21:42
 * @descriptor : 顾客买车
 */
public class Customser implements BuyCar {
  // 购车款
  private int cash;

  public int getCash() {
    return cash;
  }

  public void setCash(int cash) {
    this.cash = cash;
  }

  @Override
  public void buyCar() {
    System.out.println("顾客花--> " + cash + " 元购买了一辆车");
  }
}
