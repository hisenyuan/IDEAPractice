package com.hisen.utils;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

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
            throws DocumentException {
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

    @Test
    public void testXml() {
        String xml = "<records>"
                + "<record>"
                + "<orderStatus>Authorized</orderStatus>"
                + "<ref>000000000016</ref>"
                + "<payRef>4018465</payRef>"
                + "<amt>2999</amt>"
                + "<cur>702</cur>"
                + "<prc>0</prc>"
                + "<src>0</src>"
                + "<ord>12345678</ord>"
                + "<holder>hisenyuan</holder>"
                + "<authId>018465</authId>"
                + "<alertCode>null</alertCode>"
                + "<remark/>"
                + "<eci>07</eci>"
                + "<payerAuth>U</payerAuth>"
                + "<sourceIp>210.12.27.206</sourceIp>"
                + "<ipCountry/>"
                + "<payMethod>VISA</payMethod>"
                + "<panFirst4>4918</panFirst4>"
                + "<panLast4>5005</panLast4>"
                + "<cardIssuingCountry>HK</cardIssuingCountry>"
                + "<channelType>SPC</channelType>"
                + "<txTime>2018-03-12 17:12:35.0</txTime>"
                + "<successcode>0</successcode>"
                + "<MerchantId>12106175</MerchantId>"
                + "<errMsg>Query Successfully</errMsg>"
                + "</record>"

                + "<record>"
                + "<orderStatus>Authorized</orderStatus>"
                + "<ref>000000000016</ref>"
                + "<payRef>4018465</payRef>"
                + "<amt>2999</amt>"
                + "<cur>702</cur>"
                + "<prc>0</prc>"
                + "<src>0</src>"
                + "<ord>12345678</ord>"
                + "<holder>hisenyuan</holder>"
                + "<authId>018465</authId>"
                + "<alertCode>null</alertCode>"
                + "<remark/>"
                + "<eci>07</eci>"
                + "<payerAuth>U</payerAuth>"
                + "<sourceIp>210.12.27.206</sourceIp>"
                + "<ipCountry/>"
                + "<payMethod>VISA</payMethod>"
                + "<panFirst4>4918</panFirst4>"
                + "<panLast4>5005</panLast4>"
                + "<cardIssuingCountry>HK</cardIssuingCountry>"
                + "<channelType>SPC</channelType>"
                + "<txTime>2018-03-12 17:12:35.0</txTime>"
                + "<successcode>0</successcode>"
                + "<MerchantId>12106175</MerchantId>"
                + "<errMsg>Query Successfully</errMsg>"
                + "</record>"

                + "</records>";
        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            List<Element> list = root.selectNodes("//record");
            for (Element element : list) {
                HashMap<String, String> recordMap = new HashMap<>(32);
                for (Iterator iterator = element.elementIterator(); iterator.hasNext(); ) {
                    Element el = (Element) iterator.next();
                    if (!(el.getTextTrim().equals(""))) {
                        recordMap.put(el.getName(),el.getText());
                    }
                }
                System.out.println(JSON.toJSONString(recordMap));
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历当前节点下的所有节点
     */
    public void listNodes(Element node) {
        //如果当前节点内容不为空，则输出
        if (!(node.getTextTrim().equals(""))) {
            System.out.println(node.getName() + "---" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            listNodes(e);
        }
    }
}
