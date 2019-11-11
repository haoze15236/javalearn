package com.collection;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

public class TestMap {

	/**
	 * @param args
	 * Map接口实现 自动打包auto-boxing和解包auto-unboxing 检测Map中重复value出现的次数
	 */
	public static void main(String[] args) {
		// int h;
		// String key = "我是hash";
		// h = key.hashCode();
		// System.out.println(h);
		// System.out.println(Integer.toBinaryString(h));
		// System.out.println(h >>> 16);
		// System.out.println(Integer.toBinaryString(h >>> 16));
		// h = h ^ (h >>> 16);
		// System.out.println(h);
		// System.out.println(Integer.toBinaryString(h));
		// (h = key.hashCode()) ^ (h >>> 16);

		// Map m = new HashMap();
		// Map hm = new HashMap();
		// Map tm = new TreeMap();
		// Map ht = new Hashtable();
		// Map p = new Properties();
		// Integer count;
		// for(int i=0;i<args.length;i++){
		// count = m.containsKey(args[i])?(Integer)m.get(args[i])+1:1;
		// m.put(args[i], count);
		// }
		//
		// //课外学习 HashMap根据Key排序 使用TreeMap 创建对象的时候可以直接使用
		// Map sm = new TreeMap(new Comparator<String>(){
		//
		// @Override
		// public int compare(String i, String o) {
		// //调用对象的compareTo方法
		// return i.compareTo(o);
		// }
		//
		// });
		// sm.putAll(m);
		// System.out.println("Map根据Key排序"+sm);
		// //课外学习 HashMap根据Key排序 使用TreeMap 创建对象的时候可以直接使用
		//
		// //System.out.println(m.size()+" distinct words detected;");
		// //HashMap根据value排序
		// System.out.println("m.entrySet(): "+m.entrySet());
		// List<Map.Entry<String, Integer>> l = new ArrayList(m.entrySet());
		// Collections.sort(l, new Comparator<Map.Entry<String, Integer>>(){
		// public int compare(Entry<String, Integer> i,
		// Entry<String, Integer> o) {
		// return i.getValue().compareTo(o.getValue());
		// }
		//
		// });
		// System.out.println("Map根据value排序结果："+l);

	    SxtHashMap s= new SxtHashMap();
		//Map s = new HashMap();
		s.put(1, "haoze1");
		s.put(2, "haoze2");
		s.put(17, "haoze17");
		s.put(3, "haoze3");
		s.put("1", "HAOZE1");
		s.put("2", "HAOZE2");
		s.put("12", "HAOZE12");
		s.put("3", "HAOZE13");
		System.out.println(s);
		System.out.println(s.get(17));
		s.remove(17);
		System.out.println(s);
	}

}

class SxtHashMap<K, V> {

	Node<K, V>[] table; // 位桶数组 bucket array
	int size; // 存放的键值对的个数

	public SxtHashMap() {
		this.table = new Node[16];
	}

	public void put(K key, V value) {
		int hash = hash(key.hashCode());
		int index = myHash(hash, table.length);
		Node<K, V> newNode = new Node(hash, key, value, null);
		if (table[index] == null) {
			// 数组元素为空,则直接放入新节点
			table[index] = newNode;
		} else {
			// 数组元素不为空，则循环单项链表,不重复则拼在链表最后
			Node temp = table[index];
			while (temp != null) {
				if (temp.key.equals(key)) {
					temp.value = value;
					break;
				} else if (temp.next == null) {
					temp.next = newNode;
					break;
				}
				temp = temp.next;
			}
		}
		++size;
	}

	public V get(K key) {
		int hash = hash(key.hashCode());
		int index = myHash(hash, table.length);
		Node temp = table[index];
		while (temp != null) {
			if (temp.key.equals(key)) {
				return (V) temp.value;
			}
			temp = temp.next;
		}
		return null;
	}

	public void remove(K key) {
		int hash = hash(key.hashCode());
		int index = myHash(hash, table.length);
		Node temp = table[index], prev;
		while (temp != null) {
			prev = temp;
			temp = temp.next;
			if (prev== table[index]&&prev.key.equals(key)) {
				table[index] = temp;
				break;
			} else if (temp.key.equals(key)) {
				prev.next = temp.next;
				break;
			}
		}
		if(temp == null){
			throw new IndexOutOfBoundsException("没有找到Key:"+key+"的值");
		}else{
			--size;
		}
	}

	/**
	 * 计算哈希散列
	 * 
	 * @param key
	 * @return
	 */
	static final int hash(Object key) {
		int h;
		return (h = key.hashCode()) ^ (h >>> 16);
	}

	/*
	 * 计算数组索引
	 */
	static final int myHash(int h, int length) {
		return h & (length - 1); // h%length;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('{');

		// for(int i = 0;i<table.length;i++){
		for (Node temp : table) {
			while (temp != null) {
				s.append(temp.toString() + ",");
				temp = temp.next;
			}
		}
		s.setCharAt(s.length() - 1, '}');
		return s.toString();
	}

	static class Node<K, V> {
		int hash;
		K key;
		V value;
		Node<K, V> next;

		public Node(final int hash, final K key, final V value, final Node next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public final String toString() {
			return key + "=" + value;
		}
	}
}
