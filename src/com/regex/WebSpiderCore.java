package com.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络爬虫
 * 【1】
 * @author zee
 *
 */
public class WebSpiderCore {
	//获取url下的所有内容
	public static String getUrlContent(String url){
		StringBuilder str = new StringBuilder();
		try {
			URL dirurl = new URL(url);
			try(BufferedReader br = new BufferedReader(new InputStreamReader(dirurl.openStream(),"gbk"))){
			String temp ="";
			while((temp=br.readLine())!=null){
				str.append(temp);
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	//爬取内容中的url链接
	public static List<String> getMatchUrls(String derstr,String regexp){
		List<String> result = new ArrayList<String>();

//		Pattern reg = Pattern.compile("<a(.+?)</a>");//匹配所有<a</a>标签内的内容
//		Pattern reg = Pattern.compile("href=\"(http.+?)\"");//匹配所有href标签内的内容
		Pattern reg = Pattern.compile(regexp);
		Matcher m = reg.matcher(derstr);
		
		while(m.find()){
//			System.out.println(m.group(1));
			result.add(m.group());
		}
		return result;
	}

	public static void main(String[] args) {
		String derstr = getUrlContent("http://www.163.com");
//		System.out.println(derstr);
		List<String> result = getMatchUrls(derstr,"<a(.+?)</a>");
		for(String temp:result){
			System.out.println(temp);
		}
	}

}
