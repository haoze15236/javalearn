package com.Thread;
/**
 * ThreadLocal 每个线程互相不影响，即时使用static修饰,实际实现是通过map<Threadloacl,Object>()
 * @author zee
 *
 */
public class TesThreadLocal implements Runnable {
	private static ThreadLocal<Integer> threadlocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 100;
		}
	};

	public static void main(String[] args) throws InterruptedException {
		TesThreadLocal ttl = new TesThreadLocal();
		for (int i = 0; i <= 100; i++) {
			new Thread(ttl).start();
		}
		Thread.sleep(1000);
		System.out.println("ttl.threadloca为：" + ttl.threadlocal.get());
		System.out.println("TesThreadLocal.threadloca为："
				+ TesThreadLocal.threadlocal.get());

	}

	@Override
	public void run() {
		this.threadlocal.set((int) (Math.random() * 99));
		System.out.println(Thread.currentThread().getName() + ".threadloca为："
				+ TesThreadLocal.threadlocal.get());
	}

}
