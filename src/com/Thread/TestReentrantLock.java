package com.Thread;
/**
 * 可重入锁指有延续性的锁
 * ReentrantLock
 * @author zee
 *
 */
public class TestReentrantLock {
//	private static Lock l = new Lock();
	private static Relock l = new Relock();
	
	public static void a(){
		l.lock();
		b();
		l.unlock();
	}
	
	public static void b(){
		l.lock();
		
		l.unlock();
	}


	public static void main(String[] args) {
		TestReentrantLock.a();
	}

}
/**
 * 可重用锁
 * @author zee
 *
 */
class Relock {
	private boolean islock = false;
	private Thread lockby = null;

	public synchronized void lock() {
		while (islock&&lockby!=Thread.currentThread()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		islock = true;
		lockby = Thread.currentThread();
	}
	
	public synchronized void unlock(){
		if (!islock){
			this.notifyAll();
		}
		islock = false;
		lockby = null;
	}
}
/**
 * 不可重用锁
 * @author zee
 *
 */
class Lock {
	private boolean islock = false;
	

	public synchronized void lock() {
		while (islock) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		islock = true;
	}
	
	public synchronized void unlock(){
		if (!islock){
			this.notifyAll();
		}
		islock = false;
	}
}