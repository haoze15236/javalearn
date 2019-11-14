package com.net;
/**
 * 作业：接收对象，文件
 * 疑问：数据过大比如一张图片超过NetworkInterface.getMTU()-40个字节的长度怎么办？
 */
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.apache.commons.io.FileUtils;
// 记录日志 FileUtils.writeStringToFile(dir, "第"+count+"接收"+"\n","UTF-8", true);
/**
 * 	【1】创建接受服务
 * 		 InetSocketAddress serversock = new InetSocketAddress("localhost", 9999);
 * 		 DatagramSocket server = new DatagramSocket(serversock);
 * 	【2】创捷接受报包
 * 		 byte[] temp = new byte[1024*60];
 * 		 DatagramPacket packet = new DatagramPacket(temp,temp.length);
 * 	【3】接收报包
 * 		 server.receive(packet);
 * 	【4】从报包处理数据
 * 		 byte[] content = packet.getData();   //把字节数组还原成数据即可
 * 	【5】关闭资源
 * 		 server.close();
 * 
 * @author zee
 *
 */
public class TestUdpServer implements Runnable {
	private int port;
	private DatagramSocket server;
	DatagramPacket packet;
	private byte[] temp;
	File dir = new File("src/com/net/udplog.txt");
	public TestUdpServer(int port) {
		super();
		this.port = port;
		try {
			this.server = new DatagramSocket(new InetSocketAddress("localhost",
					port));
			this.temp = new byte[1024 * 60];
			this.packet = new DatagramPacket(temp, temp.length);
		} catch (SocketException e) {
			System.out.println("创捷接收服务失败!");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
//				this.packet.setData(new byte[1024*60]);
				this.server.receive(this.packet);
				String msg = new String(this.packet.getData(),0,this.packet.getLength());
				System.out.println(msg);
//				FileUtils.writeStringToFile(dir, msg+"\n","utf-8", true);
				if (msg.equalsIgnoreCase("quit")) {
					break;
				}
			} catch (Exception e) {
				this.server.close();
				e.printStackTrace();
			}
		}
		this.server.close();
	}

	public static void main(String[] args) throws Exception {
		TestUdpServer server1 = new TestUdpServer(9999);
		TestUdpClient client1 = new TestUdpClient(9988,"zee");
		client1.setSendTo("localhost", 8888);
		new Thread(server1).start();
		new Thread(client1).start();
		
		// File dir = new File("src/com/net/udplog.txt");
		// FileUtils.writeStringToFile(dir, "开始接收","utf-8", false);
		// InetSocketAddress serversock = new InetSocketAddress("localhost",
		// 9999);
		// DatagramSocket server = null;
		// int count = 1;
		// try {
		// //1，创建接受服务
		// server = new DatagramSocket(serversock);
		// byte[] temp = new byte[1024*60];
		// //2，创捷接受报包
		// DatagramPacket packet = new DatagramPacket(temp,temp.length);
		// while(true){
		// packet.setData(new byte[1024*60]);
		// //3，接收报包
		// server.receive(packet);
		// System.out.println("第"+count+"接收");
		// FileUtils.writeStringToFile(dir, "第"+count+"接收\n","UTF-8", true);
		// //4，从报包处理数据
		// //只要能通过字节数组还原的都可以接,但是要按照字节数组中的顺序还原
		// byte[] content = packet.getData();
		// FileUtils.writeStringToFile(dir, new String(content)+"\n","UTF-8",
		// true);
		// FileUtils.writeStringToFile(dir,
		// String.valueOf(packet.getLength())+"\n","UTF-8", true);
		// FileUtils.writeStringToFile(dir, "第"+count+"接收成功"+"\n","UTF-8",
		// true);
		//
		// System.out.println(new String(content));
		// // System.out.println(packet.getLength());
		// System.out.println("第"+count+"接收成功");
		// count++;
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// server.close();
		// e.printStackTrace();
		// }
		// //5，关闭资源
		// server.close();
		// System.out.println("结束接收");
	}

}
