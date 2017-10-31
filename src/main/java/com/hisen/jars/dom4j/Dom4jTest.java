package com.hisen.jars.dom4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Dom4j解析XML
 *
 * @author hisenyuan 2017年1月17日    下午11:19:58
 */
public class Dom4jTest {

	public static void main(String[] args) throws Exception {
		Dom4jTest d = new Dom4jTest();
		d.test();

//		当前节点名称：workers
//		当前节点名称：worker1
//		属性id:001
//		当前节点名称：高级java
//		高级java：@hisenyuan
//		当前节点名称：工号
//		工号：20110714
//		当前节点名称：地址
//		地址：北京
//		当前节点名称：座右铭
//		座右铭：唯有跑步和读书不能放弃
//		当前节点名称：worker2
//		属性id:002
//		当前节点名称：初级java
//		初级java：@hisenyuan
//		当前节点名称：学号
//		学号：20080808
//		当前节点名称：地址
//		地址：北京朝阳区
//		当前节点名称：座右铭
//		座右铭：三样东西别人抢不走：一是吃进胃里的食物,二是藏在心中的梦想,三是读进大脑里的书丨

	}

	public void test() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/main/java/com/hisen/jars/dom4j/Get_GCD_LCM.xml"));
		Element root = document.getRootElement();
		listNodes(root);
	}

	private void listNodes(Element root) {
		System.out.println("当前节点名称：" + root.getName());
		List<Attribute> list = root.attributes();
		for (Attribute attribute : list) {
			System.out.println("属性" + attribute.getName() + ":" + attribute.getValue());
		}
		if (!(root.getTextTrim().equals(""))) {
			System.out.println(root.getName() + "：" + root.getText());
		}
		//递归迭代所有子节点
		Iterator<Element> iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			listNodes(e);
		}
	}
}
