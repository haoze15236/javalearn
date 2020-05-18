package com.javase;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 转换文件的编码格式,内容保持不变 问题： 1,无法正确输出（System.out.println()）转换之后的中文？ =>
 * 设置流的读取解码格式与文本文件的字符编码格式一致 2,如何设置读取文件的解码格式？ => 直接读取字符串,按照指定编码解码成字节数组
 * 3,能否自动检测文本文件编码格式？
 * 
 * @author zee
 *
 */
public class TransformCoding {

	public static void main(String[] args) {
		// String str = "爱睡觉的噶纪录时刻";
		FileOutputStream fos = null;

		// transform("D:/training/software/workplace/javaLearn/src/com/IO/TestTransForm.java","GBK");
		transformFile("D:/training/software/workplace/MySerlvet/src/com", "gbk", "utf-8");

		// 查看当前系统的编码方式
		// System.out.println(Charset.defaultCharset().name());
		// System.out.println(System.getProperty("file.encoding"));

		// 测试设置读取文件的解码格式
		// try {
		// //printByte(str.getBytes("GBK")); // 按GBK方式解码字符串为字节数组
		// //printByte(str.getBytes("UTF-8"));
		// String s = new String(str.getBytes("GBK"), "GBK");
		// // System.out.println(s);
		// // printByte(s.getBytes("UTF-8"));
		// try {
		// fos = new FileOutputStream(new File(
		// "C:/Users/郝泽/Desktop/gbk.txt"));
		// OutputStreamWriter op = new OutputStreamWriter(fos, "gbk");
		// try {
		// op.write(s);
		// fos.flush();
		// op.flush();
		// op.close();
		// fos.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 
	 * @param filepath
	 *            文件路径名
	 * @param Charset
	 *            转换后字符集
	 */
	public static void transformFile(String filepath, String filecharset,
			String charset) {
		BufferedReader r = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		File file = new File(filepath);
		//如果是文件夹则循环更新
		if (file.isDirectory()) {
			File[] filelist = file.listFiles();
			for(File temp: filelist){
				transformFile(temp.getAbsolutePath(),filecharset,charset);
			}
		} else {
			File filecopy = new File(filepath + "copy");
			try {
				// 按文件本来字符编码创建流
				r = new BufferedReader(new InputStreamReader(
						new FileInputStream(filepath), filecharset));
				// 转换为指定编码的文件备份
				fos = new FileOutputStream(filepath + "copy");
				String line = null;
				while ((line = r.readLine()) != null) {
					//System.out.println(line);
					//printByte(line.getBytes(charset));
					fos.write((line+'\n').getBytes(charset));
					fos.flush();
				}
				r.close();
				fos.close();
				// 备份文件覆盖原文件
				fis = new FileInputStream(filepath + "copy");
				fos = new FileOutputStream(filepath);
				int temp;
				while ((temp = fis.read()) != -1) {
					fos.write(temp);
					fos.flush();
				}
				fis.close();
				fos.close();
				// 删除备份文件
				boolean b = filecopy.delete();
				System.out.println(filepath+" 从"+filecharset+" 编码转换成 "+charset+" 成功 ");
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (r != null)
						r.close();
					if (fis != null)
						fis.close();
					if (fos != null)
						fos.close();
					if (filecopy != null)
						filecopy.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 输出字节数组16进制
	 * 
	 * @param b
	 */
	public static void printByte(byte[] b) {
		for (int i = 1; i < b.length; i++) {
			System.out.print((Integer.toHexString(b[i - 1] & 0xff) + ' ')
					.toUpperCase());
			if (((i) & 0x0f) == 0)
				System.out.print("\n");
		}
		System.out.println();
	}

}
