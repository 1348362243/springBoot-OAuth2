package com.wx.spring.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MD5Util {
	private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	//加密
	public static StringBuffer toHex(byte[] bytes) {
		StringBuffer str = new StringBuffer();
		int length = bytes.length;
		for (int i = 0; i < length; i++) {
			// 转化为16进制
			str.append(Character.forDigit((bytes[i] & 0xf0) >> 4, 16));// (bytes[i] & 0xf0) >> 4 先与0xf0相与取高四位，并右移四位
			str.append(Character.forDigit((bytes[i] & 0x0f), 16));// bytes[i] & 0x0f 与0x0f相与取低四位
		}
		bytes = null;
		return str;
	}

	public static String returnStr(String str) {
		StringBuffer buffer = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//重置摘要
			md5.reset();
			//更新摘要
			md5.update(str.getBytes());
			//计算摘要
			byte messageDigest[] = md5.digest();
			buffer = toHex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
		}
		return buffer.toString();
	}

	/**
	 * 产生多少位的随机字符串
	 * <br>@description: 
	 * <br>@author: wangxin
	 * <br>@since: (版本) 作者 时间 注释 
	 * @return
	 */
	public static String getRandomStr(int len) {
		String ret = "";
		for (int i = 0; i < len; i++) {
			int randomInt = new Random().nextInt(16);
			ret += hexDigits[randomInt];
		}
		return ret;
	}

	/**
	 * @param originPasswd
	 * @return 经过MD5加密的密码
	 */
	public static String getMd5Passwd(String originPasswd, String salt) {
		return returnStr(returnStr(originPasswd) + salt);
	}


	/**
	 * @param originPasswd
	 * @return 经过MD5加密的密码
	 */
	public static String getMd5Passwd(String originPasswd) {
		return returnStr(originPasswd);
	}

	public static void main(String[] args) {
		Set<String> set =new HashSet<>();
		for(int i=0;i<1000000;i++){
			set.add(MD5Util.getMd5Passwd("xbed-" + System.currentTimeMillis()+".pdf", MD5Util.getRandomStr(10)));
		}
		System.out.println(set.size());
	}
	
	
}
