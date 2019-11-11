package com.Thread;

public class TestSinglePattern {
	//double check locking
	private volatile static TestSinglePattern instance;

	public static TestSinglePattern getInstance() {
		if (instance == null) {//性能优化,排除临界点的锁机制
			synchronized (TestSinglePattern.class) {
				if (instance == null) {
					/* 新对象时分为三步 
					 * 1,开辟内存空间
					 * 2,初始化对象信息
					 * 3,返回对象内存地址
					 * 
					 * 第3步与第二部没有逻辑的先后关系,可能存在指令重排,返回一个没有初始化的对象地址
					 * 为了避免此处的错误需要volatile修饰instance,防止指令重排
					 */
					instance = new TestSinglePattern();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		for(int i=1;i<1000;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(TestSinglePattern.getInstance().toString());
				}
			}).start();
		}
		while(true){
			try {
				Thread.currentThread().join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
