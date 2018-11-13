package com.newIns.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternUtils {
	
	
	public static void main(String[] args) {
		
//		checkHasStr("I am noob from ruoob.com.");
		
		boolean checkPirce = checkPirce("0465.02");
		
		System.out.println(checkPirce);
		
	}
	
	public static boolean checkHasStr(String content){
		
		String pattern = ".*runoob.*";
		
		boolean isMatch = Pattern.matches(pattern, content);
		
		System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
		
		return isMatch;
	}
	
	public static boolean checkIsFirst(String content){
		//查找以Java开头,任意结尾的字符串
		 Pattern pattern = Pattern.compile("^Java.*");
		 Matcher matcher = pattern.matcher("Java不是人");
		 boolean b= matcher.matches();
		 //当条件满足时，将返回true，否则返回false
		 System.out.println(b);
		 return b;
	}
	
	// 判断 价钱格式
	public static boolean checkPirce(String content){
		String pattern = "^(0|[1-9][0-9]{0,9})(\\.[0-9]{1,2})?$";
		
		boolean isMatch = Pattern.matches(pattern, content);
		
		return isMatch;
		
	}
	
	
}
