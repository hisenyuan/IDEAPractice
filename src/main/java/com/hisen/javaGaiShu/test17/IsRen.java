package com.hisen.javaGaiShu.test17;

import java.util.Scanner;

/**
 * 计算某年的某个月有多少天
 * 影响因子有是否为闰年
 *
 * @author hisenyuan 2017年2月10日    下午10:00:33
 */
public class IsRen {

  @SuppressWarnings("resource")
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("请输入年份(注：必须大于1990)：");
    int year = sc.nextInt();
    System.out.println("请输入月份");
    int month = sc.nextInt();

    boolean isRen;
    if (year % 4 == 0 && year % 100 != 0 && year % 400 == 0) {
      System.out.println(year + "闰年");
      isRen = true;
    } else {
      isRen = false;
    }

    int day = 0;
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        day = 31;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        day = 30;
        break;
      default:
        if (isRen) {
          day = 29;
        } else {
          day = 28;
        }
        break;

    }
    System.out.println(year + "年" + month + "月共有" + day + "天");
  }
}
