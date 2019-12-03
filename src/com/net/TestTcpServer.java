package com.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.io.IOUtils;

import com.net.TestTcpChatRoom.Client;

/**创建服务端
 * 【1】 使用ServerSocket创建服务器
 * 		ServerSocket server = new ServerSocket(9999);
 * 【2】 阻塞等待连接accpet()
 * 		Socket client = server.accept();
 * 【3】 操作：输入输出流操作
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String msg ;
		while (!(msg=dis.readUTF()).equalsIgnoreCase("quit")) {
			// BufferedReader br = new BufferedReader(new InputStreamReader(
			// client.getInputStream()));
			System.out.println(msg);
		}
 * 【4】 释放资源
 * 		dis.close();
		server.close();
 * @author zee
 *
 */
public class TestTcpServer {

	static CopyOnWriteArrayList<Client> clients = new CopyOnWriteArrayList();

	public static void main(String[] args) {
		System.out.println("-------------server-------------");
		ServerSocket server;
		try {
			server = new ServerSocket(9999);
			boolean serverislive = true;
			while (serverislive) {
				try {
					Socket client = server.accept();
					// 每次接收一个客户端连接新启动一个线程
					Client c = new Client(client);
					clients.add(c);
					new Thread(c).start();
				} catch (IOException e) {
					serverislive = false;// 接收一个客户端错误停止接收
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();// 使用ServerSocket创建服务器错误
		}
	}

	static class Client implements Runnable {
		private Socket client;
		private DataInputStream Receive;
		private DataOutputStream send;
		private String msg;
		private boolean isrunning = true;

		public Client(Socket client) {
			super();
			this.client = client;
			try {
				this.Receive = new DataInputStream(client.getInputStream());
				this.send = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				this.close();
			}
		}

		public void receive() {
			try {
				this.msg = this.Receive.readUTF();
			} catch (IOException e) {
				this.isrunning = false;
				this.close();
			}
		}

		public void send() {
			try {
				this.send.writeUTF(this.msg);
				this.send.flush();
			} catch (IOException e) {
				this.isrunning = false;
				this.close();
			}
		}
		
		public void sendAll(){
			for(Client temp :clients){
				if(temp!=this){
					try {
						temp.send.writeUTF(this.msg);
						temp.send.flush();
					} catch (IOException e) {
						this.isrunning = false;
						this.close();
					}
				}
			}
		}

		public void run() {
			while (this.isrunning) {
				this.receive();
//				this.send();
				this.sendAll();
			}
		}

		private void close() {
			try {
				if (null != this.client) {
					this.client.close();
				}
				if (null != this.send) {
					this.send.close();
				}
				if (null != this.Receive) {
					this.Receive.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

