package com.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestIO {

	/**
	 * \ / 名称分割符 File.separator
	 */
	public final static String SourceFileName = "src/com/IO/source.txt";
	public final static String DirFileName = "src/com/IO/dir.txt";
	public final static String path = File.separator
			+ System.getProperty("user.dir");
	public final static File SourceFile = new File(SourceFileName);
	public final static File DirFile = new File(DirFileName);

	/**
	 * @param args
	 *            输入输出流： 字节字符流： 节点处理流：
	 * @throws IOException
	 */
	/*
	 * 抽象类 InputStream FileInputStream Reader OutputStream Writer 涉及类
	 */

	// FileInputStream s = new FileInputStream(TestIO.fileName);
	// FileOutputStream os = new FileOutputStream(TestIO.fileName);
	// FileReader r = new FileReader(TestIO.fileName);
	// FileWriter w = new FileWriter(TestIO.fileName);

	public static void main(String[] args) throws IOException {
		int b = 0x2f;
		String s = "hello world 爱说大话";
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			try {
				fis = new FileInputStream(TestIO.SourceFile);
				fos = new FileOutputStream(TestIO.DirFile);
				/***
				 * 测试基础类型数据转换成字节写入文件
				 */
				 fos.write(b); // 直接写入int类型,只写一个byte
				 fos.write(int2Byte(b)); //int转byte数组,实际写入4个字节,
				 System.out.println(int2Byte(b).length);
				 fos.write(s.getBytes());// 写入String类型先转byte[]
				 //System.out.println(bytes2hex(s.getBytes()));
				 //System.out.println(byte2Int(int2Byte(b)));

				fis.close();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 先打开的后关闭
				if (fis != null)
					fos.close();
				if (fos != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*
		 * 经典范例
		 */
//		try {
//			// 创建输入流
//			fis = new FileInputStream(SourceFile); // 文件内容是：abc
//			// 一个字节一个字节的读取数据
//			int s1 = fis.read(); // 打印输入字符a对应的ascii码值97
//			int s2 = fis.read(); // 打印输入字符b对应的ascii码值98
//			int s3 = fis.read(); // 打印输入字符c 对应的ascii码值99
//			int s4 = fis.read(); // 由于文件内容已经读取完毕，返回-1
//			System.out.println((char) s1);
//			System.out.println((char) s2);
//			System.out.println((char) s3);
//			System.out.println((char) s4);
//			// 流对象使用完，必须关闭！不然，总占用系统资源，最终会造成系统崩溃！
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			fis.close();
//		}
	}

	/**
	 * 基础数据类型二进制文件想正确写入文件,都需要转成byte[]的格式 此方法提供int转byte数组
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] int2Byte(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) (i >>> 24 & 0xff);
		b[1] = (byte) (i >>> 16 & 0xff);
		b[2] = (byte) (i >>> 8 & 0xff);
		b[3] = (byte) (i & 0xff);
		return b;
	}

	public static int byte2Int(byte[] b) {
		int result = 0;
		for (int i = b.length; i > 0; i--) {
			result += (b[i - 1] << ((b.length - i) * 8))&0xff;
		}
		return result;
	}

	/**
	 * byte数组输出其二进制
	 * 
	 * @param b
	 * @return
	 */
	public static String bytes2hex(byte[] b) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			// System.out.println(b[i]+"的二进制为："+Integer.toBinaryString(b[i]&0xff));
			s.append(Integer.toBinaryString(b[i] & 0xff));
		}
		return s.toString();
	}
}
