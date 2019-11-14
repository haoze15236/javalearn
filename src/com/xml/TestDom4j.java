package com.xml;

import java.io.File;
import java.io.UnsupportedEncodingException;

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
			Document d = new SAXReader().read(new File("src/com/xml/test.xml"));
			Element root = d.getRootElement();
			System.out.println(root.getName());
			System.out.println(root.getStringValue());
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			XMLWriter writer = new XMLWriter(System.out,format);
//			writer.write(d);
//			writer.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
