package com.codenote.utils;

import java.util.Random;

public class RandomStringUtil
{
	public static void main(String[] args)
	{
		String s = genRandomStr(5);
		System.out.println(s);
	}
	
	/**
	 * 获取指定长度的一串随即字符串
	 * @param length
	 * @return
	 */
	public static String genRandomStr(int length)
	{
		char[] ca = new char[length];
		Random random = new Random();
		
		for(int i=0; i<length; i++)
		{
			ca[i] = (char)(random.nextInt(26)+97);
		}
		
		return String.valueOf(ca);
	}
	
}
