package com.javase;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SplitFile {
	private File src;
	private String destdir;
	private int blocksize;
	private int blockcount;

	public static void main(String[] args) throws IOException {
//		System.out.println(File.separator);
		SplitFile sf = new SplitFile("src/com/IO/TestRandomAccessFile.java", "src/com/IO/test", 1024);
		sf.spilt();
	}
	public SplitFile(String srcfilename, String destdir, int blocksize) {
		super();
		this.src = new File(srcfilename);
		this.destdir = destdir;
		this.blocksize = blocksize;
		this.blockcount = (int) Math.ceil(this.src.length() * 1.0 / blocksize);
	}

	public void spilt() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(this.src, "r");
		RandomAccessFile raf2 = null;
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
			raf2 = new RandomAccessFile(new File(destdir + File.separator+  (i + 1)+"-" 
					+ this.src.getName() ), "rw");
			raf2.write(block, 0, len);
		}
		raf2.close();
		raf.close();
	}

}
