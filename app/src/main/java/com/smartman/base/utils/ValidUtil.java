package com.smartman.base.utils;


import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtil {

	/**
	 * 判断是否有效的邮箱地址
	 * @param s 邮箱地址
	 * @return 返回判断结果
	 */
	public static boolean isValidEmail(String s) {
		if (TextUtils.isEmpty(s)) {
			return false;
		}
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	/**
	 * 判断是否有效的手机号
	 * @param s 手机号码
	 * @return 返回判断结果
	 */
	public static boolean isValidMobilePhone(String s) {
		if (TextUtils.isEmpty(s)) {
			return false;
		}
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	/**
	 * 判断字符串是否为数字
	 * @param str 字符串
	 * @return 返回判断结果
	 */
	public static boolean isNumeric(String str){ 
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   return isNum.matches() ; 
	}

	/**
	 * 判断密码串是否6-16位，只含有字母和数字
	 * @param str 字符串
	 * @return 返回判断结果
	 */
	public static boolean isMatchPassword(String str){
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,16}");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches() ;
	}

}
