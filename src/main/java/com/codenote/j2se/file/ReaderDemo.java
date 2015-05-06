package com.codenote.j2se.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ReaderDemo
{

	public static void main(String[] args)
	{
		String to = "tmp/to.txt";
		String from = "tmp/from.txt";
		writeFile(from, to);
	}
	
	public static void writeFile(String from, String to)
	{
		BufferedReader br = null;
		PrintWriter bw = null;
		
		try
		{
			br = new BufferedReader(new FileReader(new File(from)));
			
			bw = new PrintWriter(new FileWriter(new File(to)));
			
			int s;
			
			while((s=br.read()) != -1)
			{
				System.out.println(s);
				bw.write((char)s);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(bw != null)
				{
					bw.close();
				}
				if(br != null)
				{
					br.close();
				}
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

}
