package com.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class TestIpAndPort {

	public static void main(String[] args) throws UnknownHostException {
		/*
		 * IP
		 */
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia.getHostAddress());
		System.out.println(ia.getHostName());

		ia = InetAddress.getByName("www.shsxt.com");
		System.out.println(ia.getHostAddress());
		System.out.println(ia.getHostName());

		ia = InetAddress.getByName("123.56.138.186");
		System.out.println(ia.getHostAddress());
		// System.out.println(ia.getHostName());//反解析失败 输出IP
		/*
		 * 端口 1,区分软件 2，2个字节 0-65535 3，同一个协议下端口不能冲突
		 */
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 9000);
		InetSocketAddress is2 = new InetSocketAddress("localhost", 8080);
		System.out.println(isa.getHostName());
		System.out.println(isa.getAddress());
		System.out.println(is2.getHostName());
		System.out.println(is2.getAddress());
		System.out.println(isa.getPort());

		/*
		 * URL 1,协议 http/ https 2，域名/IP 3, 端口，默认80 4，请求资源
		 */
		try {
			URL url = new URL(
					"https://www.taobao.com/?spm=a2e15.8261149.1581860521.1.6bb629b4KY3zZ4");
			System.out.println("协议：" + url.getProtocol());
			System.out.println("域名或ip: " + url.getHost());
			System.out.println("端口: " + url.getPort());
			System.out.println("请求资源：" + url.getFile());
			System.out.println("请求资源：" + url.getPath());
			System.out.println("参数: " + url.getQuery());
			System.out.println("锚点: " + url.getRef());
			File dir =  new File("src/com/net/web.html");
			
			InputStream is = url.openStream();
			//打印网页信息
//			BufferedReader br = new BufferedReader(new InputStreamReader(is,
//					"UTF-8"));
//			String msg = null;
//			while (null != (msg = br.readLine())) {
//				System.out.println(msg);
//			}
			//直接调用FileUtils复制到文件
//			FileUtils.copyURLToFile(url,dir);
			//复制网页信息到文件
//			OutputStream os = new FileOutputStream(dir);
//			byte[] temp= new byte[1024*4];
//			int count = 0;
//			while((count = is.read(temp))!=-1){
//				os.write(temp, 0, count);
//			}
//			is.close();
//			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
