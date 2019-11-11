package com.Thread;

import java.util.Iterator;
import java.util.Map;

/**
 * 生产者消费者模式====>
 * 管程法(pop()/push())实现线程安全的通信
 * 未解决问题：多个消费者/多个生产者导致线程不安全
 * 解决办法：生产/消费 判断使用while循环阻塞判断是否可操作线程，保证线程安全
 * 
 * 信号灯法(say()/listen())实现线程安全通信
 * @author zee
 *
 */
public class TestCooperation {
	public static void main(String[] args) throws InterruptedException {
		SynContainer c = new SynContainer(new DataUnit[100], "包子铺");
		new Thread(new Productor(c), "zee").start();
		new Thread(new Productor(c), "zee2").start();
		new Thread(new Productor(c), "zee3").start();
		new Thread(new Productor(c), "zee4").start();
		new Thread(new Consumer(c), "seven").start();
		new Thread(new Consumer(c), "seven2").start();
		new Thread(new Consumer(c), "seven3").start();
		new Thread(new Consumer(c), "seven4").start();

		Map monitor = Thread.getAllStackTraces();
		while (true) {
			Thread.sleep(1000 * 3);
			System.out.println("=================线程状态监控================");
			Iterator i = monitor.keySet().iterator();
			while (i.hasNext()) {
				Thread temp = (Thread) i.next();
				System.out.println(temp.getName() + "当前状态为：" + temp.getState());
			}
		}
	}

}
// 生产者
class Productor implements Runnable {
	private SynContainer sc;
	public Productor(SynContainer sc) {
		this.sc = sc;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
//			System.out.print(Thread.currentThread().getName());
//			sc.push(new DataUnit(i));
			if((i&0x03)==0){
				sc.say("欢迎光临"+i);
			}else{
				sc.say("大家好我要生产包子了"+i);
			}
		}
	}
}
// 消费者
class Consumer implements Runnable {
	private SynContainer sc;
	public Consumer(SynContainer sc) {
		this.sc = sc;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
//			System.out.print(Thread.currentThread().getName());
			// sc.pop();
			sc.listen();
		}
	}

}
// 缓冲区
class SynContainer<D> {
	private D[] elements;
	private int count;
	private int allcount;
	private String name;

	public SynContainer(D[] elements, String name) {
		this.elements = elements;
		this.name = name;
	}

	public synchronized void push(D d) {
		while (elements.length == count) {
			System.out.println(this.name + "已经满了,快消费");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		elements[count++] = d;
		System.out.println(Thread.currentThread().getName()+"生产了:" + d.toString() + "目前" + this.name + "中还有"
				+ count);
		allcount++;
		this.notifyAll();
	}

	public synchronized D pop() {
		while (count == 0) {
			System.out.println(this.name + "已经空了,快生产");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		D d = elements[count];
		// elements[count] = null;
		System.out.println(Thread.currentThread().getName()+"消费了:" + d.toString() + "目前" + this.name + "中还有"
				+ count);
		this.notifyAll();
		return d;
	}

	// 信号灯法线程通信
	private String content;
	private boolean flag = true;
	public synchronized void say(String content) {
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"说了：" + content);
		this.content = content;
		flag = !flag;
		this.notifyAll();
	}

	public synchronized void listen() {
		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"听到了：" + this.content);
		flag = !flag;
		this.notifyAll();
	}
}
/**
 * 数据单元
 * 完整性:不存在部分生产/部分消费数据单元
 * 独立性:数据单元彼此不能影响
 * 颗粒度:生产/消费最小单位的数据单元
 * @author zee
 *
 */
class DataUnit {
	private int num;
	public DataUnit(int num) {
		super();
		this.num = num;
	}
	@Override
	public String toString() {
		return "DataUnit [num=" + num + "]";
	}
}
