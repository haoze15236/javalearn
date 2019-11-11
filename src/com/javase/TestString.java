package com.javase;

public class TestString {

	public static void main(String[] args) {
		
		/**
		 * String 不可变字符序列 
		 */
		String str = "hao1";
		System.out.println(str.hashCode());
//		str = "ze";
//		System.out.println(str.hashCode());
		String str2 = "hao1ze";
		System.out.println(str2.hashCode());
		//编译细节
		String str3 = "hao1" + "ze";  //编译器自动进行优化，相当于str3 = hao1ze;
		System.out.println(str3.hashCode());
		String str4 = "ze";
		String str5 = str + str4;
		System.out.println(str5.hashCode());
		
		/**
		 *   StringBuilder      &    StringBuffer      可变字符序列
		 * 线程不安全，效率高	               线程安全，效率低
		 */
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<=7;i++){
			sb.append((char)('a'+i));
		}
		System.out.println(sb.toString());
		
		

        /**使用String进行字符串的拼接和SringBuilder效率测试*/
		
        String str8 = "";
        //本质上使用StringBuilder拼接, 但是每次循环都会生成一个StringBuilder对象
        long num1 = Runtime.getRuntime().freeMemory();//获取系统剩余内存空间
        long time1 = System.currentTimeMillis();//获取系统的当前时间
        for (int i = 0; i < 5000; i++) {
            str8 = str8 + i;//相当于产生了10000个对象
        }
        long num2 = Runtime.getRuntime().freeMemory();
        long time2 = System.currentTimeMillis();
        System.out.println("String占用内存 : " + (num1 - num2));
        System.out.println("String占用时间 : " + (time2 - time1));
        /**使用StringBuilder进行字符串的拼接*/
        StringBuilder sb1 = new StringBuilder("");
        long num3 = Runtime.getRuntime().freeMemory();
        long time3 = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            sb1.append(i);
        }
        long num4 = Runtime.getRuntime().freeMemory();
        long time4 = System.currentTimeMillis();
        System.out.println("StringBuilder占用内存 : " + (num3 - num4));
        System.out.println("StringBuilder占用时间 : " + (time4 - time3));
	}

}
