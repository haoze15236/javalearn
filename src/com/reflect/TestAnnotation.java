package com.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;



/**
 * @author zee
 *
 *	 元注解
 *		@Documented
 *		@Retention
 *			保留周期 RetentionPolicy.RUNTIME
 *		@Target()
 *			作用范围   ElementType.ANNOTATION_TYPE
 *		@Inherited
 *
 *
 *【1】定义注解 
 *		Table.java/Column.java/belong.java
 *【2】类中使用注解
 *		TestTable.java
 *【3】解析注解：使用反射读取注解信息
 *		[1]获取类的所有有效注解(@Retention(RetentionPolicy.RUNTIME))
 *		[2]获取类的指定注解
 *		[3]获取类的属性注解
 *		[4]获取类的方法注解
 *【4】读取类的注解生成DDL语句,然后jdbc执行sql,数据库中生成相关表
 */

public class TestAnnotation {
	
	public static void main(String[] args) {
		try {
			Class clz = Class.forName("com.reflect.TestTable");
			System.out.println("---------获得类的所有注解--------------");
			Annotation[] annotations = clz.getAnnotations();
			for(Annotation temp: annotations){
				System.out.println(temp);
			}
			System.out.println("---------获得类的指定注解--------------");
			Table table =(Table)clz.getAnnotation(Table.class);
			System.out.println(table.annotationType());
			System.out.println(table.value());
			System.out.println("---------获得类的属性的注解--------------");
			Field[] fileds =clz.getDeclaredFields();
			for(Field filed:fileds){
				Column column = filed.getAnnotation(Column.class);
				if(column!=null){
					System.out.println(column.annotationType());
					System.out.println(column.name());
				}
			}
			System.out.println("---------获得类的方法的注解--------------");
			Method[] methods = clz.getDeclaredMethods();
			for(Method method:methods){
				Annotation[] temp = method.getAnnotations();
				for(Annotation t : temp){
					System.out.println(t.annotationType());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
