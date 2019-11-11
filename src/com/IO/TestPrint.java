package com.IO;

import java.io.*;

public class TestPrint {

	/**
	 * @param args
	 * 打印流
	 * PrintStream  System.out 的使用  System.
	 * 练习 
	 * 输入文件名 打印文件内容
	 * 输入内容，记录到一个文件里（log4j）
	 * PrintWriter
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		PrintStream ps = System.out;
//		ps.print("你好");
		
		//重定向
		String s = "C:\\Users\\郝泽\\Desktop\\test.java";
		PrintStream pw = new PrintStream(new FileOutputStream(s,true));
		System.setOut(pw);
		System.out.print("hello");
		//重定向回控制台
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

//		try {
//			PrintWriter pw = new PrintWriter(new FileOutputStream(s,true));
//			String fileName = null;
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			while((fileName = br.readLine())!=null){
//			if(fileName != null){
//				br = new BufferedReader(new FileReader(fileName));
//				String content = null;
//				while((content=br.readLine())!=null){
//					pw.append(content);
//					pw.println();
//				}
//			}
//			pw.flush();
//			pw.close();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
