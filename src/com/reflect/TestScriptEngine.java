package com.reflect;

import java.io.FileReader;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * ps：和教学视频不同是的，JDK7,8开始nashorn取代了Rhino,使用上有一些区别,与先前的Rhino实现相比，这带来了2到10倍的性能提升。
 * 脚本引擎JDK6.0之后添加的新功能
 * 【1】获取脚本引擎对象
 * 【2】定义变量，存储到引擎上下文中，java和脚本语言共享此变量
 * 【3】执行脚本
 * 【4】java中使用JS函数
 * 【5】js中使用java的类
 * 【6】java中调用JS文件
 * 		使用
 * @author zee
 *
 */
public class TestScriptEngine {

	public static void main(String[] args) throws Exception {
//【1】获取脚本引擎对象
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
//【2】定义变量
		engine.put("msg", "zee love seven");
		String str = "var user ={name:'haoze',age:18,school:['武工大','hand']};";
		str += "print(user.name);";//nashorn用法和rhino的println一样
//【3】执行脚本
		System.out.println("-------------执行脚本获取定义的变量---------------");
		engine.eval(str);
		engine.eval("msg = 'seven also love zee'");
		System.out.println(engine.get("msg"));
		System.out.println(engine.get("user.age"));
//【4】java中使用JS函数
		System.out.println("-------------java中使用JS函数---------------");
		//定义函数
		engine.eval("function add(a,b){var sum = a+b;return sum;}");
		//取得调用接口
		Invocable jsinvoke = (Invocable)engine;
		//使用接口执行脚本中定义的方法
		Object result =jsinvoke.invokeFunction("add", new Object[]{12,20});
		System.out.println(result);
//【5】js中使用java的类
		System.out.println("-------------js中使用java的类---------------");
		//nashorn 中没有importPackget(java.util.Arrays)的用法了;使用java类需要用下面类似的方式
		String jscode = "var Arrays = Java.type('java.util.Arrays');list = Arrays.asList([\"武工大\",\"hande\"]);";
		engine.eval(jscode);
		List<String> list =(List<String>)engine.get("list");
		for(String temp : list){
			System.out.println(temp);
		}
//【6】java中调用JS文件
		System.out.println("-------------java中调用JS文件---------------");
		//获取文件
		System.out.println(TestScriptEngine.class.getResource("nashorn.js"));
		URL jsurl = TestScriptEngine.class.getResource("nashorn.js");
		//创建文件输入流
		FileReader fr = new FileReader(jsurl.getPath());
		//执行文件内容
		engine.eval(fr);
		//释放资源
		fr.close();
	}

}

