package com.hisen.jars.poi;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author hisenyuan
 * @time 2018/1/12 17:43
 * @description 测试读取
 */
public class TestPoiExcelUtil {

  public static void main(String[] args) {
    File file = new File("C:\\work\\document\\银行信息.xlsx");
    try {
      // 每一个excelData为一行数据（存放在数组）
      List<String[]> excelData = POIExcelUtil.readExcel(file);
      for (String[] data:excelData) {
        System.out.println(Arrays.toString(data));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
