package com.reflect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

/**
 * 动态编译的两种做法：
 *  PS:	编译有包的类，需要进入包的上级目录，编译完整包名+类：java com.reflect.DynamicCompile;
 * 【1】通过JavaCompiler动态编译
 * 			compiler.run(null, null, null, tempfilepath);
 * 			第一个参数：null则默认从控制台输入编译参数
 * 			第二个参数：null则默认输出编译信息到控制台
 * 			第三个参数：null则默认输出错误信息到控制台
 * 【2】通过Runtime启动新的进程去操作,调用javac编译/java运行,
 * 			实际上Runtime.getRuntime()可以取得当前JVM的运行时系统环境
 * 			exec()实际是通过运行系统环境去执行代码,此处就是相当于命令行里执行  --------------[有些命令暂时运行报错未解决]
 * 【3】通过反射运行编译好的类
 * 			通过URLClassLoader获取动态编译的类的Class对象,使用反射调用Class对象中的main方法
 */
public class DynamicCompile {

	public static void main(String[] args) throws Exception {
		TestJavaCompiler jc = new TestJavaCompiler();
		// TestRuntime t = new TestRuntime();
	}

}

class TestJavaCompiler {
	public TestJavaCompiler() {
		String filepath = "D:/training/software/workplace/javaLearn/src/com/reflect/TestTable.java";
		// 如果只有需要编译的内容,先创建临时文件,得到filepath
		String data = "package com.reflect;public class HelloWorld {public static void main(String[] args) {System.out.println(\"hello world!\");}}";
		filepath = (DynamicCompile.class.getResource("").getPath()+ "HelloWorld" + ".java").substring(1);
		System.out.println("临时文件名：" + filepath);
		File tempfile = new File(filepath);
		try {
			FileUtils.writeStringToFile(tempfile, data, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("----------------通过JavaCompiler动态编译----------------");
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();// 获取动态编译器
		int result = compiler.run(System.in, System.out, System.err,filepath);
		System.out.println(result == 0 ? "编译成功" : "编译失败");
		System.out.println("----------------通过反射,动态执行编译好的类----------------");
		System.out.println("当前类所在路径(不含包):"+DynamicCompile.class.getResource("/"));
		System.out.println("当前类所在路径(含包):"+DynamicCompile.class.getResource(""));
		try {
			//获取类的对象
			URL[] urls = new URL[]{new URL("file:"+DynamicCompile.class.getResource("/"))};
			URLClassLoader loader = new URLClassLoader(urls);
			Class c = loader.loadClass("com.reflect."+tempfile.getName().split("\\.")[0]);  //编译有报名的类前面要拼包的路径
			
			//调用main方法
			Method m = c.getDeclaredMethod("main", String[].class);
			
			//调用static方法，第一个参数可以传null;
			//传数组需要转型成Object;
			m.invoke(c, (Object)new String[]{"11,22"});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

class TestRuntime {
	public TestRuntime() {
		System.out.println("----------------通过Runtime动态编译加运行----------------");
		try {
		Runtime run = Runtime.getRuntime();
		// 编译
		// Process process =run.exec("javac D:\\training\\software\\workplace\\javaLearn\\bin\\com\\reflect\\HelloWorld.java");
		// 运行
		Process process= run.exec("cd D:\\training\\software\\workplace\\javaLearn\\bin && java com.reflect.HelloWorld");
			// 获取错误信息
			System.out.println("--------程序执行错误信息------------");
			InputStream errin = process.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(errin,
					"gbk"));
			String errinfo = "";
			while ((errinfo = br.readLine()) != null) {
				System.out.println(errinfo);
			}
			// 获取输出信息
			System.out.println("--------程序执行输出信息------------");
			InputStream in = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,
					"gbk"));
			String outinfo = "";
			while ((outinfo = reader.readLine()) != null) {
				System.out.println(outinfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
