package com.codenote.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯法求集合
 * @author lizeyu
 *
 */
public class BackTrack
{
	public static void main(String[] args)
	{
		// 初始化一个集合，放在list里面
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		List<String> li = new ArrayList<String>();
		PowerSet(0, list, li);
	}

	// 回溯法求幂集
	public static void PowerSet(int i, List<String> list, List<String> li)
	{

		if (i > list.size() - 1)
		{
			System.out.println(li);
		}
		else
		{
			li.add(list.get(i));// 左加
			PowerSet(i + 1, list, li); // 递归方法
			li.remove(list.get(i)); // 右去
			PowerSet(i + 1, list, li);
		}
	}

}