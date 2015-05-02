package com.codenote.test;

import java.util.regex.Pattern;


public class Test1
{
	public static void main(String[] args)
	{
		String str = "hello world";
		String s = str.replaceFirst("hello", "nihao");
		System.out.println(s);

		sayHello();
		
	}
	
	
	
	public static void sayHello()
	{
		System.out.println("hello world");
		
	}
	
	

}
