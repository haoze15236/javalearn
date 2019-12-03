package com.reflect;
/**
 * 线程上下文类加载器
 * 用途: 为了避免sun公司定义的接口和外部实现类应为双亲委派加载模式而使用不用类加载器
 * 原理：每个线程都有一个上下文类加载器，默认是父线程的上下文类加载器，一般来说没有修改就是系统类加载器
 * 
 * tomcat类加载器不是双亲委派机制,每个webapp服务都有一个类加载器,加载不了才让父类加载器加载
 * 
 * OSGI(Open System Gateway Initative) 是面向java的动态模块系统，谁定义的使用谁的类加载器加载
 * 后续学习可以参考Equinox ，一个基于OSGI的框架实现
 * @author zee
 *
 */
public class TestContextClassLoader {

	public static void main(String[] args) {
		ClassLoader l =  Thread.currentThread().getContextClassLoader();
		System.out.println(l);
		ClassLoader cl = new FileSystemClassLoader();
		Thread.currentThread().setContextClassLoader(cl);//修改上下文类加载器
	    l =  Thread.currentThread().getContextClassLoader();
		System.out.println(l);
		try {
			Thread.currentThread().getContextClassLoader().loadClass("c");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
