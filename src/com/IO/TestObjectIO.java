 package com.IO;

import java.io.*;


public class TestObjectIO {

	/**
	 * @param args
	 * 对象流
	 * transient / serializable / externalizable
	 */
	public static void main(String[] args) {
		item i = new item();
		i.d = 0.12412;
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\郝泽\\Desktop\\test.java");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(i);
			os.flush();
			os.close();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\郝泽\\Desktop\\test.java"));
			item it = (item)ois.readObject();
			System.out.println("d:"+it.d+"_"+"i:"+it.i+"_"+"s:"+it.s);
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

class item implements Serializable{
	int i = 0;
	double d = 0.213;
    String s = "asdqw";
}
