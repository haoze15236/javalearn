package com.javase; 
/**
 * 
 * @author zee
 *
 */
public class TestPolym {
	
	private String name;
	public static void main(String[] args) {
		dog d = new dog();
		animalCry(d);
	}
	/**
	 * 
	 * @param a-animal 父类引用指向子类对象 使用多态便于程序维护
	 */
	public static void animalCry(animal a){
		a.shout();
	}
}
class animal{
	public void shout(){
		System.out.println("动物叫");
	}
}
class dog extends animal{
	public void shout(){
		System.out.println("汪汪汪");
	}
}
