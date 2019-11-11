//容器类全部存在于java.util包中  
//一个图 
//一个类（Collections） 
//三个知识点  
//	1.增强For循环，
//	2.auto-boxing,
//	3.泛型 
//六个接口
//	Collection set list map Iterator Comparable
//*****************************Collection*************************************
//									*                                        *
//									*										Map
//									*										 *
//					  *****************************						     *
//					  *							  *						     *
//					  *                           *						     *
//					  *                           *						     *
//			set(没有顺序不可以重复)			  list(有顺序可以重复)    		 *
//					 *                            *						     *
//				     *							  *						     *
//				     *                  *******************					 *
//				     *                  *				  *					 *
//				     *                  *                 *					 *
//				  HashSet		    LinkedList        ArrayList           HashMap/TreeMap			实现上面接口的类

package com.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class testCollection {

	public static void main(String[] args) {
		// System.out.println("Test Collection");
		Collection a = new HashSet();
		Collection b = new ArrayList();
		LinkedList c = new LinkedList();
		Collection d = new Vector();
		Map e = new HashMap();
		Map f = new TreeMap();
		Collections.shuffle(c);
//		三种循环方式
//		Iterator i = c.iterator();
//		while(i.hasNext()){
//			Item it = (Item)i.next();
//			System.out.println(it.getName());
//		}
//		for(Iterator i = c.iterator();i.hasNext();){
//			Item it = (Item)i.next();
//			System.out.println(it.getName());
//		}
////		增强for循环一般用于遍历查看容器内对象
//		for(Object i:c){
//			Item it = (Item)i;
//			System.out.println(it.getName());		
//		}
	}
}

class Item implements Comparable{
	private String name;

	public Item(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return name;
	}

	public boolean equals(Object o) {
		if (o instanceof Item) {
			Item i = (Item) o;
			return (name.equals(i.name));
		}
		return super.equals(o);  //直接使用父类(Object)的equals方法
	}

	// 一般重写equals方法同时要重写hashCode方法
//	public int hashCode() {
//		return name.hashCode();
//	}

	@Override
	public int compareTo(Object o) {
		Item i = (Item)o;
		int lastCmp = name.compareTo(i.getName());
		return lastCmp;
	}
	
}
