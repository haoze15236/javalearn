package com.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程不安全示例
 * 多个线程同时访问同一个资源
 * @author zee
 *
 */
public class TestUnsafeThread {
    static List<String> list = new ArrayList<String>();
    //线程安全的容器
//	CopyOnWriteArrayList<String> e = new CopyOnWriteArrayList<String>();
	
	public static void main(String[] args) throws InterruptedException {
		//多线程同时操作同一资源
		Account account = new Account(100, "结婚礼金");
//		Taker you = new Taker(account, 80, "阿泽");
//		Taker wife = new Taker(account, 90, "阿七");
		new Thread(new Taker(account, 90, "阿七")).start();
		new Thread(new Taker(account, 80, "阿泽")).start();
		//容器 线程不安全
		for(int i=0 ;i<10000;i++){
			new Thread(new Runnable(){
				public void run() {
					synchronized(list){
						list.add(Thread.currentThread().getName());
					}
				}
			}).start();
		}
		Thread.sleep(1000*5);
		System.out.println(list.size());
	}
	

}

class Account{
	int money;
	String accountname;
	public Account(int money, String accountname) {
		super();
		this.money = money;
		this.accountname = accountname;
	}
	
}

class Taker implements Runnable{
	private Account acc;
	private int takeMoney;
	private int takeAllMoney;
	private String takerName;
	

	public Taker(Account acc, int takeMoney, String takerName) {
		this.acc = acc;
		this.takeMoney = takeMoney;
		this.takerName = takerName;
	}

	public void run() {
		Thread.currentThread().setName(this.takerName);
		
		//临界值状态的效率提升
		if(acc.money<=takeMoney){
			System.out.println(acc.accountname+"账户余额"+acc.money);
			System.out.println(Thread.currentThread().getName()+"口袋里有"+takeAllMoney);
			return;
		}
		/**
		 * 同步块 锁住被多个线程同时访问的要修改资源
		 */
		synchronized(acc){
			//控制多线程的安全
			System.out.println(acc.accountname+"账户余额"+acc.money);
			if(acc.money<=takeMoney){
				System.out.println(Thread.currentThread().getName()+"口袋里有"+takeAllMoney);
				return;
			}
			acc.money -= takeMoney;
		}
			takeAllMoney += takeMoney;
			System.out.println(Thread.currentThread().getName()+"口袋里有"+takeAllMoney);
	}
}
