package com.Thread;
/**
 * 线程安全
 * 1,等待池形成队列
 * 2,锁机制 synchronized 同步方法/同步块
 * @author zee
 *
 *此实例中使用了代理模式
 */
public class TestSafeThread {

	public static void main(String[] args) {
		Web12306 c = new Web12306(5);
		new Passenger(c,5,"seven").start();
		new Passenger(c,5,"zee").start();
	}
}

class Web12306 implements Runnable{
	private int availiable;
	public Web12306(int availiable) {
		super();
		this.availiable = availiable;
	}
	@Override
	public void run() {
		Passenger p = (Passenger)Thread.currentThread();
		boolean flag = bookTickets(p.seats);
		if(flag){
			System.out.println("出票成功"+Thread.currentThread().getName()+"-<位置为"+p.seats);
		}else{
			System.out.println("出票失败"+Thread.currentThread().getName()+"-<位置剩余"+availiable);
		}
	}
	/*
	 * 同步方法，锁this对象/class对象
	 */
	public synchronized boolean bookTickets(int seats){
		if(availiable >= seats){
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			availiable -= seats;
			return true;
		}
		return false;
	}
}
class Passenger extends Thread{
	int seats;
	public Passenger(Web12306 w, int seats,String name) {
		super(w,name);
		this.seats = seats;
	}
}
