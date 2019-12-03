package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 通过反射动态操作类
 * 
 * 		.setAccessible(true);跳过安全检查,可以直接访问private方法或者属性
 * 		效率比较 直接new对象 1s, 反射创建对象 30s, setAccessible(true) 之后 7s
 * 
 * 【1】构造器
 * 【2】方法
 * 
 * 【3】属性
 * 
 * 【4】泛型（类加载之后泛型被擦除,Class对象是加载之后生成。因此Class对象中没有泛型信息）
 * 		ParameterizedType:表示一种参数化的类型，比如Collection<String>
 * 			属性,方法参数，返回值都是这种
 * 		GenericArrayType: 表示一种元素类型是参数化类型或者类型变量的数组类型
 * 		TypeVariable:是各种类型变量的公共父接口
 * 		WildcardType:代表一种通配符类型表达式,比如：?,?extends Number,? super Integer
 * 
 * @author zee
 *
 */
public class TestUseClass {

	public static void main(String[] args) {
		String path = "com.reflect.TestTable";
		try {
			Class<TestTable> clz = (Class<TestTable>) Class.forName(path);
			// 【1】操作构造器创造对象
			System.out.println("------------操作构造器------------");
			// [1]直接调用无参构造器
			TestTable table01 = clz.newInstance();
			// [2]调用任意构造器
			Constructor<TestTable> c = clz.getDeclaredConstructor(int.class,
					String.class);
			table01 = c.newInstance(1001, "sys_users");
			System.out.println(table01.getTableName());
			// 【2】操作方法
			System.out.println("----------操作方法-------------");
			TestTable table02 = clz.newInstance();
			Method m = clz.getDeclaredMethod("setTableName", String.class);
			m.invoke(table02, "exp_employees");
			System.out.println(table02.getTableName());
			// 【3】操作属性
			System.out.println("----------操作属性-------------");
			TestTable table03 = clz.newInstance();
			Field name = clz.getDeclaredField("tableName");
			name.setAccessible(true);// 跳过安全检查,可以直接访问private字段
			name.set(table03, "exp_report_headers");
			System.out.println(table03.getTableName());
			// 【4】操作泛型
			/*
			 * ParameterizedType:表示一种参数化的类型，比如Collection<String>
			 * GenericArrayType: 表示一种元素类型是参数化类型或者类型变量的数组类型
			 * TypeVariable:是各种类型变量的公共父接口
			 * WildcardType:代表一种通配符类型表达式,比如：?,?extends Number,? super Integer
			 */
			System.out.println("--------------操作泛型-----------");
			TestTable table04 = clz.newInstance();
			// [1]获取属性泛型类型
			Field f = clz.getDeclaredField("map");
			Type ft = f.getGenericType();
			System.out.println("#" + ft);
			if (ft instanceof ParameterizedType) { // 如果是带泛型参数类型
				Type[] genericTypes = ((ParameterizedType) ft)
						.getActualTypeArguments(); // 获取到实际泛型类型数组
				for (Type genericType : genericTypes) {
					System.out.println("泛型类型：" + genericType);
				}
			}
			// [2]获取方法参数泛型类型
			Method tablem = TestTable.class.getDeclaredMethod("setMap",
					Map.class);
			Type[] mt = tablem.getGenericParameterTypes(); // 获取方法的参数类型
			for (Type paramType : mt) {
				System.out.println("#2" + paramType);
				if (paramType instanceof ParameterizedType) { // 如果是带泛型参数类型
					Type[] genericTypes = ((ParameterizedType) paramType)
							.getActualTypeArguments(); // 获取到实际泛型类型数组
					for (Type genericType : genericTypes) {
						System.out.println("泛型类型：" + genericType);
					}
				}
			}
			// [3]获取方法返回值泛型类型
			Method tableg = TestTable.class.getDeclaredMethod("getMap", null);
			Type mrt = tableg.getGenericReturnType();
			System.out.println("#3" + mrt);
			if (mrt instanceof ParameterizedType) { // 如果是带泛型参数类型
				Type[] genericTypes = ((ParameterizedType) mrt)
						.getActualTypeArguments(); // 获取到实际泛型类型数组
				for (Type genericType : genericTypes) {
					System.out.println("泛型类型：" + genericType);
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("类不存在");
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}
