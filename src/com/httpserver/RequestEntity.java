package com.httpserver;

import java.util.HashMap;
import java.util.Map;

/**
 * 读取并解析http请求报文	获取method uri 请求参数
 * @author zee
 *
 */
public class RequestEntity {
	private Map<String, String> params = new HashMap();
	private Map<String, String> headers = new HashMap();
	private String method;
	private String uri;
	private String param;
	private String header;
	private String body;
	private String requestinfo;
	private String blank = " ";
	private String CRLF = "\r\n";

	public static void main(String[] args) {
		StringBuilder requestinfo = new StringBuilder(
				"GET /aaaa?uname=zee&pwd=123456 HTTP/1.1\r\n");
		requestinfo.append("Content-Type:text/plain").append("\r\n");
		requestinfo.append("Connection:keep-alive").append("\r\n");
		requestinfo.append("\r\n");
		requestinfo.append("<web-app>");
//		String msg = requestinfo.toString();
//		System.out.println(msg.substring(msg.indexOf("\r\n") + 2,
//				msg.indexOf("\r\n\r\n")));
		 RequestEntity test = new RequestEntity(requestinfo.toString());
		 test.parse();
//		 System.out.println(test.uri);
//		 System.out.println(test.param);
//		 System.out.println(test.body);
		 System.out.println(test.headers.get("Content-Type"));
	}
	
	

	public RequestEntity() {
		super();
	}
	
	public RequestEntity(String requestinfo) {
		this.requestinfo = requestinfo;
		parse();
	}

	private void parse() {
		// 解析请求头=====》请求方法/请求uri
		this.method = this.requestinfo.substring(0,
				this.requestinfo.indexOf(this.blank));
		this.uri = this.requestinfo.substring(this.requestinfo.indexOf("/")+1,
				requestinfo.indexOf("HTTP/") - 1);
		if (this.uri.indexOf("?") != -1) {
			String[] url = this.uri.split("\\?");
			this.uri = url[0];
			this.param = url[1];
		}
		// 解析请求参数
		this.header = this.requestinfo.substring(
				this.requestinfo.indexOf(this.CRLF)+2,
				this.requestinfo.indexOf(this.CRLF + this.CRLF));
		 String[] headerinfo = this.header.split(this.CRLF);
		 for(String infotemp: headerinfo){
			 String[] header = infotemp.split(":");
			 headers.put(header[0], header[1]);
		 }
		// 解析请求body
		this.body = this.requestinfo.substring(this.requestinfo.indexOf(this.CRLF + this.CRLF)+4);
	}

	public String getMethod() {
		return method;
	}

	public String getUri() {
		return uri;
	}

	public String getParam() {
		return param;
	}
	
	public String getRequestinfo() {
		return requestinfo;
	}

	public String getQueryStr(String queryname) {
		return params.get(queryname);
	}
}
