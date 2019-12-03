package com.reflect;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**JVM核心机制
 * 【1】类加载机制
 * 		加载
 * 			①获取字节数组(Class文件字节码,网络资源字节码,数据库字节码)
 * 			②类加载器加载到方法区,静态二进制字节码数据转换成运行时数据结构 
 * 			③堆中生成一个对应的Class对象,指向方法区中的运行数据结构
 * 		链接：
 * 			验证-确保加载的类符合JVM规范，没有安全方面的问题
 * 			准备-正式为类变量(static)分配内存并设置初始值(static int a=3,此时设置初始值a=0)，这些内存都在方法区中进行分配
 * 			解析-虚拟机常量池内(每一个类都有一个常量池)的符号引用(类名，方法名，字符串常量等等)替换成直接引用
 * 		初始化： 
 * 			执行类构造器<clinit>()方法，类构造器<clinit>()方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块组合而成
 * 			当初始化一个类时,若其父类未初始化，则先初始化其父类
 * 			初始化线程安全
 * 			主动引用(new 对象，调用静态成员，反射调用)才会初始化
 * 			被动引用(调用final成员,由于final成员是在常量池里的直接引用)不会初始化
 * 		使用/卸载
 * 【2】类加载器层次
 * 		引导类加载器bootstrap class loader
 * 		扩展类加载器extensions class loader
 * 		应用程序类加载器application class loader
 * 		双亲委派机制
 * 【3】自定义类加载器
 * 		[1]首先检查类是否已经被加载到命名空间中
 * 		[2]调用本类的findClass()方法，获取类对应的字节码
 * 		[3]调用defineClass()导入类型到方法区
 * 		注意：同一个类被不同类加载器加载，JVM不认为是相同的类
 * @author zee
 *
 */
public class JvmAnalysis {

	public static void main(String[] args) {
		ClassLoadertest.get();
		System.out.println(JvmAnalysis.class.getResource("/").getPath().substring(1));
	}

}
//自定义文件系统类加载器
class FileSystemClassLoader extends ClassLoader{
	private String rootDir;//类加载路劲
	
	public FileSystemClassLoader() {
		this.rootDir = FileSystemClassLoader.class.getResource("/").getPath().substring(1);
	}

	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir+'/';
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		//[1]首先检查类是否已经被加载到命名空间中
		Class<?> c = findLoadedClass(name);
		if(c!=null){
			return c;
		}else{
			//委派父类加载器加载
			c = this.getParent().loadClass(name);
			if(c!=null){
				return c;
			}else{
				//开始自己加载,获取类对用的字节码，调用defineClass()导入类型到方法区
				byte[] classdata = getClassData(name);
				if(classdata==null){
					throw new ClassNotFoundException();
				}else{
					c = defineClass(name,classdata,0,classdata.length);
					return c;
				}
			}
		}
	}
	//class文件内容转换成字节数组
	private byte[] getClassData(String name){
		String filepath = this.rootDir+name.replace(".", "/")+".class";
		
		try(InputStream is = new FileInputStream(filepath);
			ByteArrayOutputStream bos = new ByteArrayOutputStream()){

			byte[] classdata = new byte[1024*5];
			int count =0;
			while((count = is.read(classdata))!=-1){
				bos.write(classdata,0,count);
			}
			return bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
}

class ClassLoadertest{
	public static void get(){
		System.out.println(ClassLoader.getSystemClassLoader());//应用程序类加载器
		System.out.println(ClassLoader.getSystemClassLoader().getParent());//扩展类加载器
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());//引导类加载器,C写的，java获取不到
	
		System.out.println(System.getProperty("java.class.path"));//当前系统类加载器加载路径
		System.out.println(String.class.getClassLoader());//通过class对象可以获取到该类的加载器
		
	}
}