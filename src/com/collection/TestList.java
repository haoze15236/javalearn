package com.collection;

import java.util.*;

public class TestList {

	/**
	 * @param args
	 * Collections类的使用
	 * Link的接口实现方法
	 * comparable接口
	 */
	public static void main(String[] args) {
		Collection c = new LinkedList();
		Collection b = new ArrayList();
		Collection d = new Vector();
		for(int i=0;i<10;i++){
			c.add("a"+i);
		}
		Collections.shuffle((List)c);
		//打印时自动调用对象的toString()方法;  Object类的toString(){getClass().getName() + "@" + Integer.toHexString(hashCode())}
		//System.out.print(c);
		//Collections类比较自动调用实现Comparable接口的对象compareTo方法
		Collections.reverse((List)c);
		//System.out.print(c);
		//System.out.print(Collections.min(c));
		
		SxtArrayList sa = new SxtArrayList();
		for(int i=1;i<=20;i++)
			sa.add("a"+i);
		//sa.get(20);
		sa.remove("18");
		System.out.println(sa.toString());
	}

}

/**
 * 自定义实现ArrayList
 */
 class SxtArrayList<E>{
	private Object[] elementData;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	public SxtArrayList(){
		elementData = new Object[DEFAULT_CAPACITY];
	}
	
	public SxtArrayList(int capacity){
		elementData = new Object[capacity];
	}
	
	public boolean add(E e){
		if(size == elementData.length){
		 elementData = Arrays.copyOf(elementData,elementData.length+(elementData.length>>1));
		}
		elementData[size++]=e;
		return true;
	}
	
	public E get(int index){
		rangeCheck(index);
		return (E)elementData[index];
	}
	
	public E set(int index,E newValue){
		rangeCheck(index);
		E oldValue = (E)elementData[index];
		elementData[index] = newValue;
		return oldValue;
	}
	
	public E remove(int index){
		rangeCheck(index);
		E oldValue = (E)elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[--size] = null;
		return oldValue;
	}
	
	public boolean remove(Object obj){
		E oldValue = null;
		for(int index=0;index<size;index++){
			if(obj.equals(elementData[index])){
				oldValue = (E)elementData[index];
				remove(index);
				return true;
			}
		}
		return false;
	}
	
    /*
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     */
    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }
    
	/**
	 * 判断是否越界
	 * @param index 索引
	 */
	private void rangeCheck(int index){
		if (index>=size) {
			throw new IndexOutOfBoundsException("索引越界");
		} 
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i=0;i<size;i++){
			s.append(elementData[i]+",");
		}
		s.setCharAt(s.length()-1,']');
		return s.toString();
	}
}

