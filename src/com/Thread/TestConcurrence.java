package com.Thread;
/**并发三特征
 * 原子性 
 * 		不可分割的最小操作步骤。
 * 		volatile只能修饰变量,确保的原子性范围只有对变量的直接读取和赋值操作
 * 		synchronized 通过lock unlock可确保对象内的整个操作的原子性
 * 可见性
 * 		jvm中多线程通过工作内存跟主存交互,从主存获取到数据在工作内存修改,再返回到主存,
 * 		一个线程修改的途中其他线程能够立即看到修改后的值
 * 		volatile 	  修饰			
 *						1,当read一个volatile变量时，JMM会把该线程对应的本地内存置为无效。
 *							线程接下来将从主内存中读取共享变量。
 *						2,当write一个volatile变量时，JMM会把该线程对应的本地内存中的共享变量刷新到主内存。
 * 		synchronized 修饰
 * 						1，unlock之前将原子性操作的所有修改立即同步回主存
 * 						2，在lock之前线程工作内存中的缓存无效，重新从主内存读取
 * 有序性 (happen before)
 * 		编译时指令重排,代码顺序与实际执行顺序不同。
 * 		volatile修饰和synchronized修饰都是确保其原子性操作所在相对位置不发生改变。
 * 		volatile修饰原子性范围只有对变量的直接读取和赋值 相当于无法指令重排
 * 		synchronized修饰原子性范围包含整个对象/块 ，内部存在指令重排(DCL单例 new实例对象时)
 * @author zee
 *
 */
public class TestConcurrence {
	public static void main(String[] args) throws InterruptedException{
		Atom a = new Atom();
		for(int i=1;i<1000;i++){
			new Thread(a,"线程"+i).start();
		}
		System.out.println(Atom.a); //结果小于1000
		
	}
}
/**
 * 原子性 导致出现问题：
 * 线程1执行到1时阻塞,线程2执行完,线程1继续执行完,导致两个线程做a++操作，a只增加了1
 * @author zee
 * a++ => 实际有三步。
 * int temp = a;		1，从主存中复制a的值到工作内存
 * temp	= temp+1;		2，在工作内存中将a+1
 * a = temp;			3, 从工作内存write进主存
 * 
 * 使用条件：
 * 						对变量的写操作不依赖于当前值。
 * 						该变量没有包含在具有其他变量的不变式中。
 */
class Atom implements Runnable{
	volatile static int a = 0;
	public void run() {
		a++;
	}
}