package com.javase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TestDate {

	public static void main(String[] args) throws ParseException {
//		Date d1 = new Date();
//		System.out.println(d1.toString());
//		
//		/*日期格式化类(抽象类 DateFormat) SimpleDateFormat*/
//		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //hh 1-12 HH1-24
//		SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss");  //kk 1-24 KK1-12  可查API
//		System.out.println(s2.format(d1));
//		//反向格式话日期
//		System.out.println(s1.parse("2019-8-29 10:36:35"));
//		
//		/*日历类(抽象类 Calendar GregorianCalendar)*/
//		Calendar c = new GregorianCalendar();
//		/*获取日期相关属性*/
//		System.out.println(c.get(c.YEAR)+"年");  
//		System.out.println(c.get(c.MONTH)+1+"月");//0-11表示对应月份
//		System.out.println(c.get(c.DATE)+"日");
//		System.out.println("星期"+(c.get(c.DAY_OF_WEEK)-1)); // 1-7 星期日- 星期六
//		/*设置相关属性*/
//		c.set(Calendar.YEAR,2020);
		/*日历程序*/
//		System.out.println("请输入日期（格式为：2019-08-31）：");
//		Scanner s = new Scanner(System.in);
//		String date = s.nextLine();
//		System.out.println("您刚输入的日期为："+date);
		String date = "2019-02-14";
		String[] str = date.split("-");
		int year = Integer.parseInt(str[0]);
		int month = new Integer(str[1]);
		int day = new Integer(str[2]);
		Calendar cl = new GregorianCalendar(year,month-1,day);
		cl.set(Calendar.DATE,1);
		int dow = cl.get(cl.DAY_OF_WEEK);
		System.out.println("日\t一\t二\t三\t四\t五\t六");
		for(int i=1;i<dow;i++){
			System.out.print("\t");
		}
		for(int i=1;i<=cl.getActualMaximum(Calendar.DATE);i++){
			cl.set(Calendar.DATE, i);
			if(i == day){
				System.out.print(i+"*\t");
			}else{
				System.out.print(i+"\t");
			}
			if(Calendar.SATURDAY == cl.get(cl.DAY_OF_WEEK)){
				System.out.println();
			}
		}
	} 
}
