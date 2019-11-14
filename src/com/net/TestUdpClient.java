package com.net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/**
 *  [1] 创建发送服务													
 *  	DatagramSocket client = new DatagramSocket(9988);
 *  [2] 封装数据成字节数组   （只要能转变成字节数组的数据都可以发送）
 * 		String s ="hello udp server!";
 *  	temp = s.getBytes();
 *  [3] 将数据封装到报包
 *		接收方地址：InetSocketAddress serversock = new InetSocketAddress("localhost", 9999);
 *		封装报包：    DatagramPacket clientdata = new DatagramPacket(temp, temp.length, serversock);
 *  [4] 发送数据报包
		client.send(clientdata);
 *  [5] 关闭资源
		client.close();
 * @author zee
 *
 */
public class TestUdpClient implements Runnable {
	private int port;
	private String clientname;
	private DatagramSocket client;
	private InetSocketAddress serversock;
	
	public TestUdpClient(int port, String clientname) {
		super();
		this.port = port;
		this.clientname = clientname;
		try {
			this.client = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println("创建客户端"+clientname+"失败！");
			e.printStackTrace();
		}
	}
	
	public void setSendTo(String hostname,int toport){
	   this.serversock = new InetSocketAddress(hostname, toport);
	}
	
	public void sendMsg (byte[] msg) throws Exception{
		if(serversock == null){
			throw new Exception("接收方没有定义");
		}
		 DatagramPacket clientdata = new DatagramPacket(msg, msg.length, serversock);
		 try {
			client.send(clientdata);
		} catch (IOException e) {
			System.out.println("发送报包失败！");
			e.printStackTrace();
		}
	}
	
	public void sendString(String msg) throws Exception{
		sendMsg(msg.getBytes());
	}
	
	public void close(){
		client.close();
	}

	@Override
	public void run() {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(System.in,"utf-8"));
			String msg ;
			while(!(msg=br.readLine()).equalsIgnoreCase("quit")){
				this.sendString(msg);
			}
		} catch (Exception e) {
			this.close();
			e.printStackTrace();
		}
		this.close();
	}
	
	public static void main(String[] args) throws Exception {
//		TestUdpClient client1 = new TestUdpClient(9988,"zee");
//		client1.setSendTo("localhost", 9999);
////		System.out.println("开始发送报包");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"utf-8"));
//		String msg ;
//		while(!(msg=br.readLine()).equals("quit")){
//			client1.sendString(msg);
//		}
////		System.out.println("发送报包完毕");
//		client1.close();
		TestUdpServer server1 = new TestUdpServer(8888);
		TestUdpClient client1 = new TestUdpClient(8899,"seven");
		client1.setSendTo("localhost", 9999);
		new Thread(server1).start();
		new Thread(client1).start();
	}

}
