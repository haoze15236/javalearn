package com.reflect;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**
 * 字节码操作
 * BCEL			Byte Code Engineering Library  --
 * 												| 基于jvm的字节码指令，性能高
 * ASM 										   --
 * 
 * CGLIB		Code Generation	Library			-
 * 												|性能稍微低于前两者
 * Javassist	本次学习
 * 【1】 创建新类					
 * 【2】 操作已有的类
 * 
 * ps:
 * $0			代表this,$1代表定义的第一个参数,依次类推
 * $args 		所有参数的数组
 * $$			所有方法参数的简写  例如：move(String a,String b) 可以使用move($$) 相当于move($1,$2)
 * $cflow		方法调用的深度 --用于递归时检查
 * $r			方法返回值的类型
 * $_			方法的返回值
 * addCatch()	方法中加入try catch 块
 * $class		this,等价于$0
 * 
 * @author zee
 *
 */
public class TestByteCode {

	public static void main(String[] args) throws Exception {
		TestJavassist.createClass();
//		TestJavassist.modifyExistClass();
	}
}

class TestJavassist {
	// 创建新类
	public static void createClass() {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.reflect.Javassist");
		try {
			// [1]创建属性
			//make方法创建
			CtField id = CtField.make("private int id;", cc);
			cc.addField(id);
			//new方法创建
			CtField name = new CtField(pool.get("java.lang.String"),"name",cc);
			name.setModifiers(Modifier.PRIVATE);
			cc.addField(name);
			// [2]创建方法
			//直接有set/get方法,也可以使用make或者new的方法
			cc.addMethod(CtNewMethod.getter("getId", id));
			cc.addMethod(CtNewMethod.setter("setId", id));

			// [3]添加构造器
			// 声明构造器
			CtConstructor constructor = new CtConstructor(new CtClass[]{
					CtClass.intType, pool.get("java.lang.String")}, cc);
			// 设置构造器方法体
			constructor.setBody("{this.id=$1;this.name=$2;}");
			cc.addConstructor(constructor);
			
			//声明无参构造器
			CtConstructor nullconstuc = new CtConstructor(new CtClass[]{}, cc);
			// 设置构造器方法体
			nullconstuc.setBody("{}");			
			cc.addConstructor(nullconstuc);
			// [4]讲构造好的类写入到指定目录
			cc.writeFile(TestByteCode.class.getResource("/").getPath());
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 修改已有的类
	public static void modifyExistClass() throws Exception {
			ClassPool pool = ClassPool.getDefault();
			CtClass cc = pool.get("com.reflect.Javassist");
			// [1]获取类的信息
			byte[] bytes = cc.toBytecode();
			cc.defrost();//writeFile() toByteCode() toClass()时会冻结CtClass,需要调用defrost()解冻
			System.out.println(Arrays.toString(bytes));// 获取类的字节码信息
			System.out.println("类名(带包路径):" + cc.getName());// 获取类名(带包路径)
			System.out.println("简要类名:" + cc.getSimpleName());// 获取简要类名
			System.out.println("包名:" + cc.getPackageName());// 获取包名
			System.out.println("父类:" + cc.getSuperclass());// 获取父类
			System.out.println("接口:" + cc.getInterfaces());// 获取接口数组

			// [2]在原有类上添加新方法    并不会出现在字节码文件中，仅仅编译器存在
			System.out.println("--------------------开始添加新方法----------------");
			CtMethod setName = CtMethod.make(
					"public void setName(String name){this.name = name;}", cc);
			cc.addMethod(setName);
			//new 方法添加
			CtMethod getName = new CtMethod(pool.get("java.lang.String"),
					"getName", new CtClass[]{}, cc);// 申明方法返回值&参数
			getName.setModifiers(Modifier.PUBLIC);// 申明方法权限
			getName.setBody("return this.name;");// 填充方法内容
			getName.insertBefore("System.out.println(\"在方法最前面加代码\");");
			getName.insertAfter("System.out.println(\"在方法后面加代码(return之前)\");");
			cc.addMethod(getName);
			// [3]通过反射调用新生成的方法
			System.out.println("-----------------通过反射调用新方法----------------");
			Class clz = cc.toClass();// 获取Class对象
			Object obj = clz.newInstance();
			Field f = clz.getDeclaredField("name");
			f.setAccessible(true);
			f.set(obj, "zee");
			Method gn = clz.getDeclaredMethod("getName",
					new Class[]{});
			String name = (String) gn.invoke(obj, null);
			System.out.println(name);
	}
	
	
}
