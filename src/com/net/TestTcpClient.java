package com.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 创建客户端
 * 【1】建立连接：使用Socket创建客户端 + 服务的地址和端口
  		Socket client = new Socket("localhost",9999);
 * 【2】操作：输入输出流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
  		String msg;
		while (!(msg = br.readLine()).equalsIgnoreCase("quit")) {
			dos.writeUTF(msg);
			dos.flush();
		}
 * 【3】释放资源
  		dos.close();
		client.close();
 * 		
 * @author zee
 *
 */
public class TestTcpClient {

	public static void main(String[] args) throws Exception {
		System.out.println("-------------client-------------");
		Socket client = new Socket("localhost", 9999);
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String msg;
		while (!(msg = br.readLine()).equalsIgnoreCase("quit")) {
			dos.writeUTF(msg);
			dos.flush();
			System.out.println(dis.readUTF());
		}
//		String msg = "hello";
//		dos.writeUTF(msg);
//		dos.flush();
		br.close();
		dos.close();
		client.close();
	}
}