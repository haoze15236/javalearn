package com.httpserver;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
/**
 * 目标：
 * 【1】多线程处理
 * 【2】接收请求,返回响应
 * @author zee
 *
 */
public class Dispatcher implements Runnable {
	private Socket connection;
	private InputStream in;
	private OutputStream out;
	private RequestEntity request;
	private ResponseEntity respose;
	private boolean islive;

	public Dispatcher() {
	}

	public Dispatcher(Socket connection) {
		super();
		this.connection = connection;
		try {
			in = connection.getInputStream();
			out = connection.getOutputStream();
			this.islive = true;
		} catch (IOException e) {
			this.close();
			this.islive = false;
			System.out.println("客户端连接失败");
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("获取请求内容");
		this.receive();
		if (this.request != null) {
			System.out.println("请求内容获取成功");
			System.out.println(this.request.getRequestinfo());
			System.out.println("准备响应内容");
			this.send();
			System.out.println(this.respose.toString());
			System.out.println("响应内容准备完毕");
		}
	}

	// 生成响应信息
	private void send() {
		try (BufferedOutputStream bos = new BufferedOutputStream(out)) {
			String sourcepath;
			if (request.getUri().indexOf(".") != -1) {
				// 有后缀的uri代表资源
				if (request.getUri() == null || request.getUri().equals("")) {
					sourcepath = "index.html";
				} else {
					sourcepath = request.getUri();
				}
				// 访问实际的文件资源
				InputStream is = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(sourcepath);
				this.respose = new ResponseEntity(IOUtils.toByteArray(is));
				this.respose.Init(200);
				System.out.println(this.respose.toString());
			} else {
				// 否则触发servlet
				Mapping map = new Mapping();
				Servlet servlet = map.getServlet(this.request.getUri());
				System.out.println("servlet成功创建："+servlet);
				if(servlet!=null){
					this.respose=servlet.service(this.request);
					this.respose.Init(200);
				}else{
					this.respose = new ResponseEntity("");
					this.respose.Init(500);
				}
			}
			bos.write(this.respose.getBytes());
			bos.flush();
		} catch (IOException e) {
			this.respose.Init(500);
			e.printStackTrace();
		}
	}

	// 获取请求信息
	private void receive() {
		byte[] temp = new byte[1024 * 1024];
		int len;
		try {
			len = in.read(temp);
			if (len != -1) {
				this.request = new RequestEntity(new String(temp, 0, len));
			}
		} catch (IOException e) {
			this.close();
			this.islive = false;
			System.out.println("读取请求失败！");
			e.printStackTrace();
		}
	}

	private void close() {
		IOUtils.closeQuietly(connection, in, out);
	}

}
