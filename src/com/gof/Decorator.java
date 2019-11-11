package com.gof;
/**
 * 装饰器设计模式
 * @author zee
 * 抽象组件:需要装饰的抽象对象（接口或者抽象父类）
 * 具体组件：需要装饰的对象
 * 抽象装饰类：包含了对抽象组件的引用以及装饰者共有的方法
 * 具体装饰类：被装饰对象
 */
public class Decorator {
	public static void main(String[] args) {
		Person p1 = new Person("郝泽",25);
		PersonWork student = new Student(p1);
		PersonWork programer = new Programer(p1);
		System.out.println(student.doSth());
		System.out.println(programer.doSth());
		

	    programer = new Programer(student);
		System.out.println(programer.doSth());
	}
}

/**
 * 抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
 * @author zee
 *
 */
interface PersonWork{
   public String doSth();
}
/**
 * 具体构件（Concrete Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
 * @author zee
 */
class Person implements PersonWork{
	private String name;
	private int	age;
	private String dosth ="吃饭睡觉呼吸";
	
	Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public String doSth() {
		return age+"岁"+name+dosth;
	}
}
/**
 * 抽象装饰（Decorator）角色：实现抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
 * @author zee
 *
 */
abstract class Work implements PersonWork{
	private PersonWork person;
	
	public Work(PersonWork person){
		this.person = person;
		
	}
	public String doSth() {
		return this.person.doSth();
	}
}
/**
 * 具体装饰（ConcreteDecorator）角色：继承抽象装饰的相关方法，并给具体构件对象添加附加的责任。
 * @author zee
 *
 */
class Student extends Work{
    public Student(PersonWork person){
    	super(person);
    }
	public String doSth() {
		return super.doSth()+"学习";
	}
}

class Programer extends Work{
    public Programer(PersonWork person){
    	super(person);
    }
	public String doSth() {
		return super.doSth()+"敲代码";
	}
}
