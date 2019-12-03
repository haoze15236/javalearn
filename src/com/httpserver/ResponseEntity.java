package com.httpserver;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装响应报文
 * 【1】字符串形式响应报文
 * 【2】字节数组形式响应报文
HTTP/1.1 200 OK
Date: Tue Nov 19 23:52:40 CST 2019
Content-type: text/html;charset=UTF-8
Content-length: 114

<html><head><title>服务器响应成功</title></head><body>shsxt server终于回来了。。。。</body></html>

 * @author zee
 *
 */
public class ResponseEntity {
	private OutputStream out;
	private StringBuilder response;
	private Map<String, Object> headers;
	private String CRLF = "\r\n";
	private String blank = " ";
	private byte[] body;
	private byte[] responsebytes;

	public ResponseEntity() {
		super();
		headers = new HashMap();
		response = new StringBuilder();
	}
	
	//返回信息是字符串格式
	public ResponseEntity(String content) {
		this();
		this.body = content.getBytes();
	}
	
	public ResponseEntity(byte[] body) {
		this();
		this.body = body;
	}
	
	public void Init(int code){
		//响应报文状态行
		statusLineInit(code);
		//响应报文头
		headersInit();				
		//响应正文
		bodyInit();
	}
	

	public void statusLineInit(int code) {
		response.append("HTTP/1.1").append(blank);
		response.append(code).append(blank);
		switch (code) {
			case 200 :
				response.append("OK").append(CRLF);
				break;
			case 404 :
				response.append("NOT FOUND").append(CRLF);
				break;
			case 500 :
				response.append("SERVER ERROR").append(CRLF);
				break;
		}
	}
	
	private void headersInit() {
		for (Map.Entry<String, Object> header : headers.entrySet()) {
			response.append(header.getKey()).append(":")
					.append(header.getValue().toString()).append(this.CRLF);
		}
		response.append(this.CRLF);
	}
	
	private void bodyInit(){
		this.responsebytes = new byte[this.response.toString().getBytes().length+this.body.length];
		System.arraycopy(this.response.toString().getBytes(), 0, this.responsebytes, 0, this.response.toString().getBytes().length);
		System.arraycopy(this.body, 0, this.responsebytes,this.response.toString().getBytes().length,this.body.length);

	}

	
	private void headersDefaultInit(){
		this.setHeader("Date", new Date());
		this.setHeader("Content-type", "text/html;charset=UTF-8");
		headersInit();
	}

	public void setHeader(String name, Object value) {
		headers.put(name, value);
	}

	public byte[] getBytes() {
		return this.responsebytes;
	}

	public String toString() {
		return new String(this.responsebytes);
	}
}
