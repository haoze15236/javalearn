package com.Thread;
/**
 * 线程状态
 * NEW  							新建线程对象
 * RUNNABLE						    start()/yield()/阻塞状态结束/JVM调度
 * BLOCK							IO(read/write)阻塞方法/
 * TIMED_WAITING					sleep()
 * WAITING							wait()/join()
 * @author zee
 */
public class TestThreadState {
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 局部类
		 * @author zee
		 *
		 */
		class Test implements Runnable {
			public void run() {
				for (int i = 0; i < 20; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i%2==0 && Thread.currentThread().getName().equals("zee")){
						Thread.yield();
					}
					System.out.println(Thread.currentThread().getName()
							+ "===>" + i);
				}
			}
		}

		Thread t1 = new Thread(new Test(),"zee");
		Thread t2 = new Thread(new Test(),"seven");
		t1.setDaemon(true);
		System.out.println(t1.getState()); // NEW 状态

		t1.start();
		t2.start();
		while (t1.getState() != Thread.State.TERMINATED) {
			Thread.sleep(100);
			System.out.println(t1.getName() + "目前的状态为" + t1.getState());
			System.out.println(t2.getName() + "目前的状态为" + t2.getState());
//			System.out.println(Thread.currentThread().getName() + "目前的状态为"
//					+ Thread.currentThread().getState());
			// Thread.currentThread().join();
		}

	}

}
