package com.IO;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class TestCommonsIO {
	public static void main(String[] args) throws IOException {
		String dir = "D:/training/software/workplace/javaLearn/src/com/IO";
		long len = FileUtils.sizeOf(new File(dir));
		System.out.println(len);
		//可过滤的遍历目录集
		Collection<File> files = FileUtils
				.listFiles(new File(dir), FileFilterUtils.or(
						new SuffixFileFilter("java"), new SuffixFileFilter(
								"txt"), EmptyFileFilter.NOT_EMPTY),
						DirectoryFileFilter.INSTANCE);
		for (File temp : files) {
			System.out.println(temp.getAbsolutePath());
		}
		
		//读取文件内容
		String dirfile = "D:/training/software/workplace/javaLearn/src/com/IO/TestCommonsIO.java";
		String msg = FileUtils.readFileToString(new File(dirfile), "utf-8");
		System.out.println(msg);
		
		//写出内容
		String srcfile = "D:/training/software/workplace/javaLearn/src/com/IO/TestCommonsIO_copy.txt";
//		FileUtils.write(new File(srcfile), "hello world", "UTF-8", false);
		
		//复制内容
		FileUtils.copyFile(new File(dirfile), new File(srcfile));
	}

}
