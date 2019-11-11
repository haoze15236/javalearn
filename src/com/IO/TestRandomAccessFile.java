package com.IO;
import java.io.File;
import java.io.IOException;
/**
 * 随机读取和写入流 RandomAccessFile
 */
import java.io.RandomAccessFile;

public class TestRandomAccessFile {

	public static void main(String[] args) throws IOException {
		// readBlock(new File("src/com/IO/TestTransForm.java"),1024,new
		// byte[1024]);
//		splitFile("src/com/IO/TestRandomAccessFile.java", 1024);
	}

	public static void splitFile(String srcfilename, int blocksize)
			throws IOException {
		File srcfile = new File(srcfilename);
		RandomAccessFile raf = new RandomAccessFile(srcfile, "r");
		RandomAccessFile raf2 = null;
		int blockcount = (int) Math.ceil(srcfile.length() * 1.0 / blocksize);
		int len;
		byte[] block = new byte[blocksize];
		for (int i = 0; i < blockcount; i++) {
			// 分块读取文件
			System.out
					.println("block=================================================>"
							+ (i + 1));
			raf.seek(blocksize * i);
			len = raf.read(block);
			System.out.println(new String(block, 0, len));
			// 分块写出文件
			raf2 = new RandomAccessFile(new File(srcfilename + '-' + i), "rw");
			raf2.write(block, 0, len);
		}
		raf2.close();
		raf.close();
	}

	/**
	 * 指定起始位置读取到文件末尾
	 * @throws IOException
	 * 
	 *  RandomAccessFile 读写文件时，不管文件中保存的数据编码格式是什么   使用 RandomAccessFile对象方法的 readLine() 
	 *  都会将编码格式转换成 ISO-8859-1 所以 输出显示是还要在进行一次转码
	 */
	public static void readFromSeek() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(TestIO.SourceFile, "r");
		raf.seek(2);
		System.out.println(new String(raf.readLine().getBytes("ISO-8859-1"),
				"utf-8"));
		raf.close();
	}

}
