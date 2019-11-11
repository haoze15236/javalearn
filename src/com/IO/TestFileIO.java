package com.IO;

import java.io.*;

public class TestFileIO {

	/**
	 * 字节节点流 
	 * FileInputStream 读取文件内容 FileOutputStream 复制文件 
	 * 字符节点流
	 * FileReader/FileWriter 避免字符编码问题
	 */
	public static void main(String[] args) {
		int b;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Reader fr = null;
		Writer fw = null;
		try {
			try {
				fis = new FileInputStream(TestIO.SourceFile);
				fos = new FileOutputStream(TestIO.DirFile);
				// while ((b = fis.read()) != -1) {
				// System.out.println((char) b);
				// fos.write((char) (b));
				// }
				/**
				 * 使用字节数组读取与写入,如果文件和本地系统字符集编码一致,读单个字节也可以
				 */
				byte[] flush = new byte[1];
				while ((b = fis.read(flush)) != -1) {
					// 读到文件末尾由于flush数组是一个字节一个字节改变，会留有历史数据
					// 具体可参看InputStream源码
					fos.write(flush,0,b);
					fos.flush();
				}
				fis.close();
				fos.close();

				// 字符流
				fr = new FileReader(TestIO.SourceFile);
				fw = new FileWriter(TestIO.DirFile,true);
				char[] charflush = new char[4];
				while((b = fr.read(charflush)) != -1){
					fw.write(charflush);
				}
				fw.close();
				fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 先打开的后关闭
				if (fis != null)
					fos.close();
				if (fos != null)
					fis.close();
				if (fw != null)
					fw.close();
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
