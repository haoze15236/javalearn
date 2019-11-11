package com.IO;

import java.io.*;

public class TestTransForm {

	/**
	 * @param args
	 * 转换流   字节流》字符流
	 * InputStreamReader
	 * OutputStreamWriter
	 * System.in(接受窗口输入)理解阻塞式方法
	 */
	public static void main(String[] args) {
		try {
//			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("C:\\Users\\郝泽\\Desktop\\test.java",true));
//			osw.write("Hello World!");
//			System.out.print(osw.getEncoding());
//			osw.write("Hello World! Again");
//			osw.flush();
//			osw.close();  //不close会写到内存里不在文件中

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String s = null;
			s = br.readLine();
			while(s!= null){
				if(s.equalsIgnoreCase("exist")){
					break;
				}
				System.out.println(s);
				s = br.readLine();
			}
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
