package com.yinww.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
	private static String EMPTY = ""; 
	
	private static String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static String NUM = "0123456789";
	private static String SPECIAL = "~!@#$%^&*()-+={}|[]:;<>?,.";
	private static String DIR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	/**
	 * 从str中获取长度为size的随机字符串
	 * @param str, 从str取值
	 * @param size, 字符串长度
	 * @return
	 */
	public static String getString(String str, int size){
		if(size <= 0) {
			return EMPTY;
		}
		
		StringBuilder s = new StringBuilder();
		Random random = new Random();
		int len = str.length();
		for (int i = 0; i < size; i++) {
			int b = random.nextInt(len);
			s.append(str.charAt(b));
		}
		return s.toString();
	}
	
	/**
	 * 获取长度为size的随机字符串
	 * @param size, 字符串长度
	 * @return
	 */
	public static String getString(int size){
		return getString(STR, size);
	}

	/**
	 * 获取长度为size的仅为数字的随机字符串
	 * @param size, 字符串长度
	 * @return
	 */
	public static String getNumber(int size){
		return getString(NUM, size);
	}

	/**
	 * 获取长度为size的仅为目录(2位)的随机字符串
	 * @param size, 字符串长度
	 * @return
	 */
	public static String getDir(){
		return getString(DIR, 2);
	}

	/**
	 * 获取长度为size的包含特殊字符随机字符串
	 * @param size, 字符串长度
	 * @return
	 */
	public static String getStringSpecial(int size){
		if(size <= 0) {
			return EMPTY;
		}
		
		StringBuilder s = new StringBuilder();
		int len = STR.length();
		int specialLen = SPECIAL.length();
		Random random = new Random();
		String str = "010010001001010010";
		int strLen = str.length();
		for (int i = 0; i < size; i++) {
			int a = random.nextInt(strLen);
			if(str.charAt(a) == '0') { // 如果随机取值为0, 则取普通字符; 为1则取特殊字符
				int b = random.nextInt(len);
				s.append(STR.charAt(b));
			} else {
				int b = random.nextInt(specialLen);
				s.append(SPECIAL.charAt(b));
			}
		}
		return s.toString();
	}

	/**
	 * 获取length个, 长度为size的随机字符串
	 * @param size, 字符串长度
	 * @param length, 个数
	 * @return
	 */
	public static List<String> getStrings(String str, int size, int length){
		if(size <= 0) {
			return null;
		}
		
		Random random = new Random();
		int len = str.length();
		List<String> result = new ArrayList<String>(length);
		for (int n = 0; n < length; n++) {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < size; i++) {
				int b = random.nextInt(len);
				s.append(str.charAt(b));
			}
			result.add(s.toString());
		}
		return result;
	}

	/**
	 * 获取length个, 长度为size的随机字符串
	 * @param size, 字符串长度
	 * @param length, 个数
	 * @return
	 */
	public static List<String> getStrings(int size, int length){
		return getStrings(STR, size, length);
	}


	/**
	 * 获取length个, 长度为size的仅为数字的随机字符串
	 * @param size, 字符串长度
	 * @param length, 个数
	 * @return
	 */
	public static List<String> getNumbers(int size, int length){
		return getStrings(NUM, size, length);
	}

	/**
	 * 获取length个, 长度为size的包含特殊字符随机字符串
	 * @param size, 字符串长度
	 * @param length, 个数
	 * @return
	 */
	public static List<String> getStringSpecials(int size, int length){
		if(size <= 0) {
			return null;
		}
		
		int len = STR.length();
		int specialLen = SPECIAL.length();
		Random random = new Random();
		String str = "010010001001010010";
		int strLen = str.length();

		List<String> result = new ArrayList<String>(length);
		for (int n = 0; n < length; n++) {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < size; i++) {
				int a = random.nextInt(strLen);
				if(str.charAt(a) == '0') { // 如果随机取值为0, 则取普通字符; 为1则取特殊字符
					int b = random.nextInt(len);
					s.append(STR.charAt(b));
				} else {
					int b = random.nextInt(specialLen);
					s.append(SPECIAL.charAt(b));
				}
			}
			result.add(s.toString());
		}
		return result;
	}

	/**
	 * 获取length个, 长度为size的仅为目录(2位)的随机字符串
	 * @param size, 字符串长度
	 * @param length, 个数
	 * @return
	 */
	public static List<String> getDirs(int length){
		return getStrings(DIR, 2, length);
	}

}
