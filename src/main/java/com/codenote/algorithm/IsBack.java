package com.codenote.algorithm;

public class IsBack
{
	public static boolean isBack(String s)
	{
		if (s.length() <= 1)
		{
			return true;
		}
		else if (s.charAt(0) != s.charAt(s.length() - 1))
		{
			return false;
		}
		else
		{
			return isBack(s.substring(1, s.length() - 1));
		}
	}

}
