package com.Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * compare and swap  属于原子性操作
 * 具体原理：
 * 内存地址V，旧的预期值A，即将要更新的目标值B
 * CAS指令执行时，当且仅当内存地址V的值与预期值A相等时，
 * 将内存地址V的值修改为B，否则就什么都不做。
 * 
 * JUC(java.util.concurrent)里实现
 * 
 * AtomicInteger
 * @author zee
 *
 */
public class TestCas {
//	static AtomicInteger count = new AtomicInteger(0);
	
	static int count = 0;
	
	public static void increase(){
//		count.getAndIncrement();//原子性操作
		count++;
	}

	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<1000;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					TestCas.increase();
				}
			}).start();
		}
		Thread.sleep(1000*5);
//		System.out.println(TestCas.count.get());
		System.out.println(count);
	}

}
