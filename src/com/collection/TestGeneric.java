package com.collection;

import java.util.*;

public class TestGeneric {

	/**
	 * @param args
	 * 各种Collection使用泛型
	 * Iterator使用泛型
	 * 检测Map中重复value出现的次数使用泛型
	 */
	public static void main(String[] args) {
		Map<Item,Integer> m = new TreeMap<Item,Integer>(new Comparator<Item>(){

			public int compare(Item i1,Item i2) {
				return i1.compareTo(i2);
			}
			
		}
);
		m.put(new Item("Haoze"), 1);
		m.put(new Item("Aqi"), 2);
		m.put(new Item("WIFI"), 2);
		m.put(new Item("Meimei"), 3);
		System.out.println(m);
	}
}

/**
 * 泛型类
 * */

class MyCollection<T>{
	Object[] objs = new Object[5];
	
	public void set(T t,int index){
		objs[index] = t;
	}
	
	public T get(int index){
		return (T)objs[index];
	}
}
