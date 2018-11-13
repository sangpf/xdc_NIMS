/**
 * 
 */
package com.newIns.tools;

/**
 * @Description 验证输入格式
 * @author Guan
 * @time 2016年7月14日 下午3:13:42
 */

public class VerifyPattern {
	/**
	 * @Title: isNumeric  
	 * @Author: Guan
	 * @Description: 验证字符串是否为数字
	 * @param str
	 * @return boolean
	 * @Time 2016年7月14日 下午3:14:11
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
