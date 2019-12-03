package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 【1】获取类的对象
 * 【2】理解Class类
 * 		jvm加载类(或者当加载器[class loader]的defineClass()被JVM调用)时,会创建Class对象保存类的所有信息,同一个类只有一个Class对象。
 * 【3】通过Class类获取类的信息（构造器，方法，属性等）
 * @author zee
 *
 */
public class TestGetClass {

	public static void main(String[] args) {
		String path = "com.reflect.TestTable";
		try {
// 【1】获取类的对象的三种方式
			System.out.println("-------------【1】--------------");
			//forName
			Class clz = Class.forName(path);
			System.out.println("类的名字：" + clz.getName() + "----hashcode:"
					+ clz.hashCode());
			//.class
			Class strclz = String.class;
			System.out.println(strclz.hashCode());
			//getClass()
			Class pathclz = path.getClass();
			System.out.println(pathclz.hashCode());
// 【2】理解Class对象
			System.out.println("----------------【2】----------------");
			//每个类只会有一个Class对象
			System.out.println(strclz.hashCode() == pathclz.hashCode());
			//数组通过元素类型和维度来分别创建对象
			int[] arr01 = new int[10];
			int[][] arr02 = new int[10][3];
			int[] arr03 = new int[20];
			byte[] arr04 = new byte[10];
			System.out.println("数组01："+arr01.getClass().hashCode());
			System.out.println("数组02："+arr02.getClass().hashCode());
			System.out.println("数组03："+arr03.getClass().hashCode());
			System.out.println("数组04："+arr04.getClass().hashCode());
//	【3】获取对象信息
			System.out.println("-----------------【3】-----------");
			//[1]获取类的名字
			System.out.println(clz.getName());
			System.out.println(clz.getSimpleName());
			//[2]获取属性信息
			System.out.println("--------获取属性信息-------------");
			Field f = clz.getField("tabletext");//只能获取public的属性
			Field df = clz.getDeclaredField("id");//获取任意属性
			System.out.println(f.getName());
			System.out.println(df.getName());
			//[3]获取方法信息
			System.out.println("--------获取方法信息-------------");
			Method m = clz.getMethod("getId", null);//第二个参数传入参数类型对象的Class对象
			Method dm = clz.getDeclaredMethod("inittabletext", null);
			System.out.println(m.getName());
			System.out.println(dm.getName());
			//[4]获取构造器
			System.out.println("--------获取构造器--------------");
			Constructor c =  clz.getConstructor();
			Constructor dc =  clz.getDeclaredConstructor(int.class,String.class);
			System.out.println(c);
			System.out.println(dc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
