package com.httpserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.apache.commons.io.IOUtils;
/**
 * 【1】 创建服务器
 * 【2】 多线程处理网页请求  Dispacher.java
 * 【3】 释放资源 IOUtils.closeQuietly();
 * @author zee
 *
 */
public class Server {
	private ServerSocket server;
	private boolean islive = false;

	public Server() {
	}

	public Server(int port) {
		try {
			this.server = new ServerSocket(port);
			islive = true;
		} catch (IOException e) {
			islive = false;
			IOUtils.closeQuietly(server);
			e.printStackTrace();
		}
	}

	public void start() {
		int count = 1;
		while (islive) {
			try {
				new Thread(new Dispatcher(this.server.accept())).start();
				System.out.println("============第"+count+"次请求成功");
				count++;
			} catch (IOException e) {
				System.out.println("客户端连接失败");
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		islive = false;
		IOUtils.closeQuietly(server);
	}

	public static void main(String[] args) {
		// 封装逻辑调用
		 Server httpserv = new Server(8888);
		 httpserv.start();

		// 原始代码逻辑
//		ServerSocket server;
//		try {
//			server = new ServerSocket(8888);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//			return;
//		}
//		while (true) {
//			try {
//				Socket client = server.accept();
//				System.out.println("一个客户端建立了连接....");
//				InputStream in = client.getInputStream();
//				byte[] temp = new byte[1024 * 1024];
//				int len = in.read(temp);
//				System.out.println("接收请求长度为：" + len + "字节");
//				if (len != -1) {
//					System.out.println(new String(temp, 0, len));
//					// }
//					StringBuilder content = new StringBuilder();
//					content.append("<html>");
//					content.append("<head>");
//					content.append("<title>");
//					content.append("服务器响应成功");
//					content.append("</title>");
//					content.append("</head>");
//					content.append("<body>");
//					content.append("shsxt server终于回来了。。。。");
//					content.append("</body>");
//					content.append("</html>");
//					int size = content.toString().getBytes("UTF-8").length; // 必须获取字节长度
//					System.out.println(size);
//					StringBuilder responseInfo = new StringBuilder();
//					String blank = " ";
//					String CRLF = "\r\n";
//					// 返回
//					// 1、响应行: HTTP/1.1 200 OK
//					responseInfo.append("HTTP/1.1").append(blank);
//					responseInfo.append(200).append(blank);
//					responseInfo.append("OK").append(CRLF);
//					// 2、响应头(最后一行存在空行):
//					/*
//					 * Date:Mon,31Dec209904:25:57GMT
//					 * Server:shsxt Server/0.0.1;charset=GBK
//					 * Content-type:text/html Content-length:39725426
//					 */
//					responseInfo.append("Date:").append(new Date())
//							.append(CRLF);
//					// responseInfo.append("Server:").append("shsxt Server/0.0.1")
//					// .append(CRLF);
//					responseInfo.append("Content-type:text/html;charset=UTF-8")
//							.append(CRLF);
//					responseInfo.append("Content-length:").append(size)
//							.append(CRLF);
//					responseInfo.append(CRLF);
//					// 3、正文
//					responseInfo.append(content.toString());
//
//					// 写出到客户端
//					BufferedWriter bw = new BufferedWriter(
//							new OutputStreamWriter(client.getOutputStream(),
//									"UTF-8"));
//					bw.write(responseInfo.toString());
//					bw.flush();
//					in.close();
//					client.close();
//				}
//			} catch (IOException e) {
//				System.out.println("错误了");
//			}
//		}
	}
}
