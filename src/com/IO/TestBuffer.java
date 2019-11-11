package com.IO;

import java.io.*;
import java.net.URL;

public class TestBuffer {

	/**
	 * @param args
	 * 处理流：使用了装饰器设计模式
	 * 缓冲流：相当于缓冲装饰
	 * BufferedInputStream/BufferedOutputStream  字节缓冲
	 * BufferedReader/BufferedWriter 字符缓冲
	 * 转换流：相当于转换装饰
	 * InputStreamReader/OutputStreamWriter 字节转字符(可设置解码格式)
	 */
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		try {
			// 操作文件
			// br = new BufferedReader(new FileReader(TestIO.SourceFile));
			 bw = new BufferedWriter(new FileWriter(TestIO.DirFile));
			// 操作System.in / System.out
			// br = new BufferedReader(new InputStreamReader(System.in));
			// bw = new BufferedWriter(new OutputStreamWriter(System.out));
			// while (!(line = br.readLine()).equals("exit")) {
			// bw.write(line);
			// bw.newLine();
			// bw.flush();
			// }
			// 操作网络资源保存为文件
			br = new BufferedReader(new InputStreamReader(new URL(
					"https://www.sxt.cn/Java_jQuery_in_action/ten-bufferedbyte.html").openStream()));
			bw = new BufferedWriter(new FileWriter(new File("src/com/IO/test.html")));
			while ((line = br.readLine()) != null) {
				 bw.write(line);
				 bw.newLine();
				 bw.flush();
			}
			bw.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
