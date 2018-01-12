package com.hisen.jars.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author hisenyuan
 * @time 2018/1/12 16:35
 * @description 利用POI读取excel表格
 */
public class POIExcelUtil {

  private static Logger logger = Logger.getLogger(POIExcelUtil.class);
  private final static String XLS = "xls";
  private final static String XLSX = "xlsx";

  public static List<String[]> readExcel(File file) throws IOException {
    // 检查文件
    checkFile(file);
    Workbook workBook = getWorkBook(file);
    // 返回对象,每行作为一个数组，放在集合返回
    ArrayList<String[]> rowList = new ArrayList<>();
    if (null != workBook) {
      for (int sheetNum = 0; sheetNum < workBook.getNumberOfSheets(); sheetNum++) {
        // 获得当前sheet工作表
        Sheet sheet = workBook.getSheetAt(sheetNum);
        if (sheet == null) {
          continue;
        }
        // 获得当前sheet的开始行
        int firstRowNum = sheet.getFirstRowNum();
        // 获得当前sheet的结束行
        int lastRowNum = sheet.getLastRowNum();
        // 循环除了第一行外的所有行
        for (int rowNum = firstRowNum; rowNum < lastRowNum; rowNum++) {
          // 获得当前行
          Row row = sheet.getRow(rowNum);
          if (row == null) {
            continue;
          }
          // 获得当前行开始的列
          short firstCellNum = row.getFirstCellNum();
          // 获得当前行的列数
          int lastCellNum = row.getPhysicalNumberOfCells();
          String[] cells = new String[row.getPhysicalNumberOfCells()];
          // 循环当前行
          for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
            Cell cell = row.getCell(cellNum);
            cells[cellNum] = getCellValue(cell);
          }
          rowList.add(cells);
        }
      }
    }
    return rowList;
  }

  /**
   * 取单元格的值
   */
  private static String getCellValue(Cell cell) {
    String cellValue = "";
    if (cellValue == null) {
      return cellValue;
    }
    // 把数字当成String来读，防止1读成1.0
    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
      cell.setCellType(Cell.CELL_TYPE_STRING);
    }
    // 判断数据的类型
    switch (cell.getCellType()) {
      // 数字
      case Cell.CELL_TYPE_NUMERIC:
        cellValue = String.valueOf(cell.getNumericCellValue());
        break;
      // 字符串
      case Cell.CELL_TYPE_STRING:
        cellValue = String.valueOf(cell.getStringCellValue());
        break;
      // 布尔
      case Cell.CELL_TYPE_BOOLEAN:
        cellValue = String.valueOf(cell.getBooleanCellValue());
        break;
      // 公式
      case Cell.CELL_TYPE_FORMULA:
        cellValue = String.valueOf(cell.getCellFormula());
        break;
      // 空
      case Cell.CELL_TYPE_BLANK:
        cellValue = "";
        break;
      // 错误
      case Cell.CELL_TYPE_ERROR:
        cellValue = "非法字符";
        break;
      default:
        cellValue = "未知类型";
        break;
    }
    return cellValue;
  }

  /**
   * 获得工作簿对象
   */
  private static Workbook getWorkBook(File file) {
    String filename = file.getName();
    Workbook workbook = null;
    try {
      InputStream is = new FileInputStream(file);
      if (filename.endsWith(XLS)) {
        // 2003
        workbook = new HSSFWorkbook(is);
      } else if (filename.endsWith(XLSX)) {
        // 2007
        workbook = new XSSFWorkbook(is);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return workbook;
  }

  /**
   * 检查文件
   */
  private static void checkFile(File file) throws IOException {
    if (null == file) {
      logger.error("文件不存在");
      throw new FileNotFoundException("文件不存在！");
    }
    // 获取文件名
    String filename = file.getName();
    // 判断是否为excel文件
    if (!filename.endsWith(XLS) && !filename.endsWith(XLSX)) {
      logger.error(filename + "不是excel文件");
      throw new IOException(filename + "不是excel文件");
    }
  }
}
