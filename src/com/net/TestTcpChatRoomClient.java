package com.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

/**
 * 创建多人聊天室客户端
 * @author zee
 *
 */
public class TestTcpChatRoomClient {

	public static void main(String[] args) throws Exception {
		System.out.println("-------------client-------------");
		Socket client = new Socket("localhost", 9999);
		Receive r = new Receive(client);
		Send s = new Send(client);
		new Thread(r).start();//启动接收线程
		new Thread(s).start();//启动发送线程
	}
}

class Receive implements Runnable {
	private Socket client;
	private DataInputStream Receive;
	private String msg;
	private boolean islive = false;
	
	public Receive(Socket client) {
		super();
		this.client = client;
		try {
			Receive = new DataInputStream(client.getInputStream());
			this.islive = true;
		} catch (IOException e) {
			IOUtils.closeQuietly(this.client,this.Receive);
			e.printStackTrace();
		}
	}

	public void receive(){
		try {
			this.msg = this.Receive.readUTF();
			System.out.println(msg);
		} catch (IOException e) {
			this.islive = false;
			IOUtils.closeQuietly(this.client,this.Receive);
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(this.islive){
			receive();
		}
	}
}

class Send implements Runnable {
	private Socket client;
	private DataOutputStream send;
	private String msg;
	
	public Send(Socket client) {
		super();
		this.client = client;
		try {
			send = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			IOUtils.closeQuietly(this.client,this.send);
			e.printStackTrace();
		}
	}
	
	
	public void send() {
		try {
			this.send.writeUTF(this.msg);
			this.send.flush();
		} catch (IOException e) {
			IOUtils.closeQuietly(this.client,this.send);
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.print("请输入聊天室昵称：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.msg = br.readLine();
			this.send();//发送昵称
			while (!(this.msg = br.readLine()).equalsIgnoreCase("quit")) {
				this.send();
			}
		} catch (IOException e) {
			IOUtils.closeQuietly(this.client,this.send);
			e.printStackTrace();
		}
	}
}