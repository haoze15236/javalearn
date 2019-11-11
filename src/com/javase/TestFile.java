package com.javase;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class TestFile {

	public static void main(String[] args) {
		File f = new File("C:\\Users\\郝泽\\Desktop\\backup");
		System.out.println(System.getProperty("user.dir"));// 不写绝对路径时自动创建到这个相对路径下
//		try {
//			f.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println("File是否存在：" + f.exists());
		System.out.println("File是否是目录：" + f.isDirectory());
		System.out.println("File是否是文件：" + f.isFile());
		System.out.println("File最后修改时间："
				+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(f
						.lastModified())));
		//System.out.println("File的大小："+(double)f.length() +"KB");//f.length()只获取文件的大小,并没有汇总计算文件夹大小
		System.out.println("File的大小："+ new DecimalFormat("#.00").format((double)f.length()/1024) +"KB");
		System.out.println("File的文件名：" + f.getName());
		System.out.println("File的目录路径：" + f.getPath());
		printFile(f, 1);
		
	}
	/**
	 * 打印目录递归树
	 * @param file 文件名
	 * @param level 递归层次
	 */
	public static void printFile(File file,int level){
		for(int i=0;i<level;i++){
			System.out.print("--");
		}
		System.out.println(file.getName());
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file2 : files) {
				printFile(file2, level+1);
			}
		}
	}
	
	public static void count(File file){
		
	}
}
