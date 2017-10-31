package com.hisen.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author : yhx
 * @date : 2017/10/31 17:37
 * @descriptor : 利用dom4j解析xml文件，可以取xml中的列表数据(<a><b>最多只能取这种级别</b></a>)，也可以取其中的一个
 */
public class ParseXMLUtil {
  /**
   * 解析XML
   *
   * @param resultXML 待解析的XML
   * @param mapList 列表第一个为父节点，其他为子节点
   */
  public static List parseResultXML(String resultXML, List<String> mapList)
      throws ParseException, DocumentException {
    Map rowMap;
    ArrayList rowList = new ArrayList();
    if (resultXML == null) {
      return rowList;
    }
    Document resultDoc = DocumentHelper.parseText(resultXML);
    resultDoc.setXMLEncoding("UTF-8");
    Element root = resultDoc.getRootElement();
    List<Element> outputList = resultDoc.selectNodes("//" + mapList.get(0));
    for (Object outputObj : outputList) {
      rowMap = new HashMap();
      for (String str : mapList) {
        rowMap.put(str, root.elementTextTrim(str));
      }
      Element element = (Element) outputObj;
      Iterator iterator = element.elementIterator();
      while (iterator.hasNext()) {
        Element attr = (Element) iterator.next();
        rowMap.put(attr.getName().trim(), attr.getText().trim());
      }
      rowList.add(rowMap);
    }
    return rowList;
  }
}
