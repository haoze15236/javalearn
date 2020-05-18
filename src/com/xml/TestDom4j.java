package com.xml;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * 为什么接口的方法可以实现具体逻辑，返回一个值？
 * @author zee
 *
 */
public class TestDom4j{
	
	public static void main(String[] args) throws Exception {
		
		try {
//			Document d = new SAXReader().read(new File("src/com/xml/test.xml"));
//			Element root = d.getRootElement();
//			System.out.println(root.getName());
//			List<Element> elements = root.elements("servlet");
//			for(Element servlet:elements){
//				System.out.println(servlet.elementText("servlet-name"));
//				System.out.println(servlet.elementText("servlet-class"));
//			}
//			
//			List<Element> servlets = root.elements("servlet-mapping");
//			for(Element servlet:servlets){
//				System.out.println(servlet.elementText("servlet-name"));
//				System.out.println(servlet.elementText("url-pattern"));
//			}
//			System.out.println(root.getStringValue());    //输出节点内所有内容
			
			Document d = new SAXReader().read(new File("src/com/xml/source.xml"));
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(System.out,format);
			writer.write(d);
			writer.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
