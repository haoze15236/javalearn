package com.Thread;
/**
 * lambda表达式简化接口实现
 * @author zee
 *
 */
public class TestLambda {
	/**
	 * 静态内部类
	 */
	static class Test implements Runnable{

		@Override
		public void run() {
			for(int i=0;i<100;i++){
				System.out.println(Thread.currentThread().getName()+"一边听歌");
			}
			
		}
		
	}

	public static void main(String[] args) {
//		new Thread(new Test(),"郝泽").start();
//		new Thread(new Test(),"阿七").start();

		/**
		 * 局部内部类
		 * @author zee
		 *
		 */
		 class Test2 implements Runnable{
				public void run() {
					for(int i=0;i<100;i++){
						System.out.println(Thread.currentThread().getName()+"一边听歌");
					}
					
				}
		 }	

//		new Thread(new Test2(),"郝泽").start();
//		new Thread(new Test2(),"阿七").start();
		
		/**
		 * 匿名内部类 必须借助接口或者父类
		 */
		new Thread(new Runnable(){ 
			public void run() {
				for(int i=0;i<100;i++){
					System.out.println(Thread.currentThread().getName()+"一边听歌");
				}
				
			}
		}).start();
		
		/**
		 * JDK8 使用lambda简化
		 */
//		new Thead(()->{
//			for(int i=0;i<100;i++){
//				System.out.println(Thread.currentThread().getName()+"一边听歌");
//			}
//		}).start();
	}

}
