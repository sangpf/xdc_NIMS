package com.newIns.tools.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.newIns.tools.StrUtils;

public class RegexUtils {
	
	
	public static boolean isNumeric(String str){
		
		if(StrUtils.isEmpty(str)){
			return false;
		}
		
		Pattern pattern = Pattern.compile("[0-9]*");
		
		Matcher isNum = pattern.matcher(str);
		
		if(!isNum.matches()){
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		boolean numeric = isNumeric("");
		
		System.out.println(numeric);
		
	}
	
}
