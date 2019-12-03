package com.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.io.IOUtils;

/**创建多人聊天室服务端
 * @author zee
 *
 */
public class TestTcpChatRoom {
	private int port;
	private ServerSocket server;
	private CopyOnWriteArrayList<Client> clients = new CopyOnWriteArrayList();
	boolean serverislive = false;


	public static void main(String[] args) {
		TestTcpChatRoom chatroom = new TestTcpChatRoom(9999);
		chatroom.monittoring();
	}
	public TestTcpChatRoom(){
		serverislive = true;
	}
	
	public TestTcpChatRoom(int port){
		this();
		this.port = port;
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("服务器启动错误...");
			e.printStackTrace();
		}
	}
	
	public void monittoring(){
		while (serverislive) {
			try {
				Socket client = this.server.accept();
				// 每次接收一个客户端连接新启动一个线程
				Client c = new Client(client);
				clients.add(c);
				new Thread(c).start();
			} catch (IOException e) {
				serverislive = false;// 接收一个客户端错误停止接收
				IOUtils.closeQuietly(server);
				e.printStackTrace();
			}
		}
	}

    class Client implements Runnable {
		private Socket client;
		private DataInputStream Receive;
		private DataOutputStream send;
		private String msg;
		private boolean isrunning = true;
		private String name;

		public Client(Socket client) {
			super();
			this.client = client;
			try {
				this.Receive = new DataInputStream(client.getInputStream());
				this.send = new DataOutputStream(client.getOutputStream());
				receive();//第一次连接时需要输入用户名
				this.name = this.msg;
				this.msg = this.name +"进入了聊天室";
				this.sendAll();//通知聊天室成员
				this.msg = "恭喜你："+this.name+"成功登录！";
				this.send();//通知当前用户
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
						temp.send.writeUTF(this.name+"说："+this.msg);
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

