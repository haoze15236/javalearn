package com.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**  
 * regular expression 
 * 测试工具 regexBuddy
 * 
 * 【1】普通字符：字母，数字，汉字，下划线，以及没有特殊定义的标点符号
 * 【2】转移字符：\n换行符 \t制表符  \^$.(){}?+*|[]这些字符都需要转义
 * 【3】标准字符集合：大写表示取反
 * 		\d：0~9任意一个数字 
 * 		\w：任意一个字母，数字，下划线。即A~Z,a~z,0~9,_
 * 		\s: 匹配任意一个空格,制表符,换行符等空白字符
 * 		. : 匹配除换行符外的任意一个字符，如果要匹配包括换行符在内的任意一个字符使用[\s\S]
 * 【4】自定义字符集合：[]括号匹配方式，能够匹配括号内的任意一个字符，或的关系
 * 		^:方括号里面是取反的意思,[^a3c]:匹配除了a,3,c之外任意一个字符
 * 		-:匹配一个集合内的任意一个字符，如：[f-k]:匹配从f到k的任意一个字符
 * 		[^A-F0-3]:匹配A~F,0~3之外的任意一个字符
 * 	ps:[]中除了^,-之外的特殊字符都代表字符本身，成为普通字符。
 * 		标准字符集合在[]中仍然是代表其集合，.变成普通字符，如：[\d.\-+]:匹配0~9任意数字 . + -
 * 【5】量词 ：
 * 		1，量词只作用于前一个表达式,如\d\d{3}:匹配任意四个数字,(\d\d){3}:匹配任意6位数字
 * 		2，默认贪婪模式,匹配越多越好,在{}后加个?,变成非贪婪模式,匹配越少越好
 * 		{n}:表达式重复n次,
 * 		{m,n}:表达式至少重复m次,最多重复n次
 * 		{m,}:表达式至少重复m次
 * 		?:匹配表达式0次或者1次,相当于{0,1}
 * 		+:相当于{1,}
 * 		*:相当于{0,}
 * 【6】字符边界：匹配符合某种条件的位置而不是字符,具有零宽属性
 * 		^:匹配字符串开始的位置
 * 		$:匹配字符串结尾的位置
 * 		\b：匹配一个字符边界，字符前面或者后面不全是\w
 * 【7】匹配模式：
 * 		IGNORECASE:忽略大小写模式
 * 		SINGLELINE:单行模式
 * 		MULTILINE:多行模式 若想匹配最开头和最后结尾使用\A \Z
 * 【8】选择符和分组
 * 		|：左右两边表达式之间"或"的关系,匹配左边或者右边
 * 		():捕获组。以左边括号顺序为捕获组排序,通过反向引用可以直接引用捕获组内容。$1,$2,$3......
 * 		   由于反向引用会在内存中保存匹配的字符串,如果字符串太多影响效率，可以在左括号后面加?:设置成非捕获组，提高效率
 * 		如：(?:[a-z]{2}):匹配到的两个字符不会保存在内存中
 * 【9】预搜索[零宽断言][环视]
 * 		(?=exp):断言自身位置的后面能匹配表达式exp
 * 		(?<=exp):断言自身位置的前面能匹配表达式exp
 * 		(?!exp):断言自身位置的后面不能匹配表达式exp
 * 		(?<!exp):断言自身位置的前面不能匹配表达式exp
 * 【10】
 * @author zee
 *
 */
public class RegexBasic {

	public static void main(String[] args) {
		//【1】新建正则表达式对象
		Pattern p = Pattern.compile("([a-z]+)([0-9]+)");//\需要转义
		//【2】创建Matcher对象
		Matcher m =p.matcher("asd124&&asf532");
		//【3】输出匹配的结果
//		System.out.println(m.find());
//		System.out.println(m.group());
//		System.out.println(m.find());
//		System.out.println(m.group());
		while(m.find()){
			System.out.println(m.group());
			System.out.println(m.group(1));  //输出捕获组1的内容
			System.out.println(m.group(2));  //输出捕获组2的内容
		}
		//【4】替换操作
		System.out.println(m.replaceAll("#"));
		//【5】分割操作
		String str = "ad123fsa123asd";
		String[] arr = str.split("[a-zA-z]+");
		System.out.println(Arrays.toString(arr));
	}

}
