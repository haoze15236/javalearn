package com.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/*
 * 1,使用List和Map存放多个图书信息，遍历并输出。其中商品属性：编号，名称，单价，出版社;使用商品编号作为Map中的key。
 * 2,使用HashSet和TreeSet存储多个商品信息，遍历并输出;其中商品属性：编号，名称，单价，出版社;要求向其中添加多个相同的商品，验证集合中元素的唯一性。
*/
public class CollectionHomeWork {

	public static void main(String[] args) {
		List<Book> l = new ArrayList<Book>();
		l.add(new Book(1,"Java基础",123.53,"北京出版社"));
		l.add(new Book(1,"Java基础",123.53,"北京出版社"));
		l.add(new Book(1,"Java基础",123.53,"北京出版社"));
//		System.out.println(l);
		Map m = new HashMap();
		m.put(1,new Book(1,"Java基础1",123.53,"北京出版社"));
		m.put(2,new Book(2,"Java基础2",123.53,"北京出版社"));
		m.put(17,new Book(16,"Java基础16",123.53,"北京出版社"));
//		System.out.println(m);
		Set<Book> s = new HashSet<Book>();
		s.add(new Book(1,"Java基础",123.53,"北京出版社"));
		s.add(new Book(1,"Java基础",123.53,"北京出版社"));
		System.out.println(s);
		
//		Iterator<Book> i = s.iterator();
//		while(i.hasNext()){
//			Book temp = i.next();
//			System.out.println(temp.name);
//		}
	}

}

class Book implements Comparable<Book>{
	int id;
	String name;
	Double price;
	String press;
	public Book(int id, String name, Double price,String press) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.press = press;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			if(Objects.equals(this.id, ((Book) obj).id)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
	
	@Override
	public String toString() {
		return "id:"+this.id+" name:"+this.name+" price:"+this.price+" press:"+ this.press+"";
	}
	@Override
	public int compareTo(Book b) {
		if(b.id > this.id){
			return 1;
		}else if(b.id == this.id){
			return 0;
		}else{
			return -1;
		}
	
	}
}
