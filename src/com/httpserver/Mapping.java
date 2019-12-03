package com.httpserver;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 通过uri返回对应类的对象
 * @author zee
 *
 */
public class Mapping {
	private String configfile = "src/com/httpserver/web.xml";
	private Document configdoc ;
	private Element root;
	
	public Mapping() {
		super();
		try {
			this.configdoc = new SAXReader().read(new File(configfile));
			this.root = configdoc.getRootElement();
		} catch (DocumentException e) {
			System.out.println("配置文件解析错误");
			e.printStackTrace();
		}
	}
	
	public Servlet getServlet(String uri){
		try {
			return (Servlet)Class.forName(getServletClass(getServletName(uri))).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			return null;
		}
	}
	
	private String getServletClass(String servletname){
		List<Element> servletlists = this.root.elements("servlet");
		for(Element servlet:servletlists){
			if(servlet.elementText("servlet-name").equalsIgnoreCase(servletname)){
				return servlet.elementText("servlet-class");
			}
		}
		return "";
	}
	
	private String getServletName(String uri){
		List<Element> mappinglists = this.root.elements("servlet-mapping");
		for(Element mapping:mappinglists){
			List<Element> urllists = mapping.elements("url-pattern");
			for(Element pattern : urllists){
				if(pattern.getText().equalsIgnoreCase(uri)){
					System.out.println("获取到servlet-name:"+ mapping.elementText("servlet-name"));
					return mapping.elementText("servlet-name");
				}
			}
		}
		return "";
	}

	public static void main(String[] args) {
		Mapping map = new Mapping();
		String uri = "hello";
		System.out.println(map.getServletName(uri));
		Servlet servlet = map.getServlet(uri);
		System.out.println(servlet);
	}

}
