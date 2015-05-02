package com.codenote.j2se.regex;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex
{
	public static void main(String[] args)
	{
//		System.out.println(Pattern.matches("b.g", "bug"));			//匹配任何单个字符
//		System.out.println(Pattern.matches("zo*", "z"));			//匹配0至多个在它之前的字符
//		System.out.println(Pattern.matches("zo*", "zoo"));
//		System.out.println(Pattern.matches("b[aui]g", "bug"));		//匹配括号中的任何一个字符
//		System.out.println(Pattern.matches("se{2}d", "seed"));		//匹配确定的 n 次
//		System.out.println(Pattern.matches("se{2,3}d", "seeed"));	//最少匹配 n 次且最多匹配 m 次,如seeeed即为false
//		System.out.println(Pattern.matches("9+", "9999"));			//最少匹配 n 次且最多匹配 m 次,如seeeed即为false
		System.out.println(Pattern.matches("ab?", "a"));			//匹配前面的子表达式零次或一次,相当于 {0,1}
		
//		System.out.println(Pattern.matches("^hello.*", "helloworld"));		//.*即表示任意字符任意次
//		System.out.println(Pattern.matches("^hello.{3}", "helloyou"));		//表示hello后面再跟3个字符
//		System.out.println(Pattern.matches("^/question/\\d+$", "/question/232323"));
//		System.out.println(Pattern.matches("[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.com", "lzy89610@163.com"));
//		
//		isMatch("^Java.*", "Java不是人");
	}
	
	public static void isMatch(String regex, String input)
	{
		Pattern pattern = Pattern.compile(regex);   
		Matcher matcher = pattern.matcher(input);   
		boolean b = matcher.matches(); 
		System.out.println(b);  
	}
	
	/**
	 * 以多条件作为分割符，这里是以逗号、空格、竖线三个符号出现一次或多次作为分割符
	 */
	public static void split()
	{
		Pattern pattern = Pattern.compile("[, |]+");
		String[] strs = pattern.split("Java Hello World Java,Hello,,World|Sun");
		for (int i = 0; i < strs.length; i++)
		{
			System.out.println(strs[i]);
		}
	}
	
	
	

}
