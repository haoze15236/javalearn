package com.collection;

import java.util.LinkedList;

public class SxtLinkedList<E> {
	private int size;
	private Node first;
	private Node last;
	
	/**
	 * add link last
	 * @param element
	 */
	public void add(E element){
		Node newNode = new Node(last,element,null);
		if(this.first == null){
			this.first = newNode;
			this.last = newNode;
		}else{
			this.last.next =newNode;
			this.last = newNode;
		}
		++size;
	}
	

	public void add(int index,E element){
		if(index == size){
			add(element);
		}else{
			checkRange(index);
			final Node temp =  getNode(index);
			final Node newNode = new Node(null,element,temp);
			final Node prev = temp.prev;
			if(prev == null){
				this.first = newNode;
			}else{
				prev.next = newNode;
				newNode.prev = prev;
			}
		}	
	}
	
	private Node getNode(int index){
		checkRange(index);
		if (index < (size>>1)) {
			Node<E> node = this.first;
			for(int i=0;i<index;i++){
				node = node.next;
			}
			return node;
		}
		Node<E> node = this.last;
		for(int i=0;i<size-index-1;i++){
			node = node.prev;
		}
		return node;
	}
	
	
	public E get(int index){
		return (E)getNode(index).element;
	}
	
	
	public E remove(int index){
		final Node temp = getNode(index);
		final E e = (E)temp.element;
		final Node prev = temp.prev;
		final Node next = temp.next;
		if(prev == null){
			this.first = next;
			next.prev = null;
		}else if(next == null){
			this.last = prev;
			prev.next = null;
		}else{
			prev.next = next;
			next.prev = prev;
		}
		temp.element = null;
		temp.prev = null;
		temp.next = null;
		--this.size;
		return e;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("[");
		Node temp = this.first;
		while(temp != null){
			s.append(temp.element+",");
			temp=temp.next;
		}
		s.setCharAt(s.length()-1, ']');
		return s.toString();
	}
	
	private void checkRange(int index){
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException("索引越界: index:"+index+" size:"+this.size);
	}
	/**
	 * 
	 * @author zee
	 *链表元素节点
	 * @param <E>
	 */
	private static class Node<E>{
		private Node prev;
		E element;
		private Node next;
	    Node(Node prev, E element,Node next) {
			this.prev = prev;
			this.next = next;
			this.element = element;
		}
	}
	
	public static void main(String[] args) {
		SxtLinkedList<String> l = new SxtLinkedList<String>();
		//LinkedList<String> l = new LinkedList<String>();
		l.add("a1");
		l.add("a2");
		l.add("a3");
		l.add("a4");
		System.out.println(l);
		System.out.println(l.get(2));
		System.out.println(l.remove(0));
		l.add(3,"a4");
		System.out.println(l);
	}
	
}
