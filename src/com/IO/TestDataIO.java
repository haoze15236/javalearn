package com.IO;

import java.io.*;

public class TestDataIO {

	/**
	 * @param args
	 * ByteArrayInputStream:来源是内存的字节数组
	 * ByteArrayOutputStream:输出到内存的字节数组中
	 * 装饰流
	 * DataInputStream
	 * DataOutputStream
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		byte[] src = new byte[2048];
		byte[] dir = new byte[2048];
		InputStream is = null;
		//src = "talk is cheap show me the code".getBytes();
//		节点流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		FileOutputStream out = new FileOutputStream(TestIO.DirFile);
//		装饰类处理流
		DataOutputStream dos = new DataOutputStream(out);
		//dos.writeBoolean(false);
		//dos.writeChar('好');
		dos.writeUTF("我和我的祖国");
		src = out.toByteArray();
		System.out.println(src.length);
		dos.close();
		
//		is = new ByteArrayInputStream(src);
		is = new FileInputStream(TestIO.SourceFile);
		DataInputStream dis = new DataInputStream(is);
		char s = dis.readChar();
		System.out.println(s);
//		dis.readFully(dir);
//		System.out.println(dir.length);
		dis.close();
	}
}
