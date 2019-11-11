package com.collection;

import java.util.*;

public class TestSet {

	/**
	 * @param args
	 * 待完成内容：Set容器的交集，快速合并
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection c = new HashSet();
		Collection c2 = new HashSet();
		c.add("1");
		c.add("2");
		c2.add("2");
		c2.add("4");
		c.addAll(c2);
		c.retainAll(c2);
		System.out.print(c);
	}

}
