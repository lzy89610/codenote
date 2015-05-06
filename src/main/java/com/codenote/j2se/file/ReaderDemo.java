package com.codenote.j2se.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ReaderDemo
{

	public static void main(String[] args) throws IOException
	{
		String to = "tmp/to.txt";
		String from = "tmp/from.txt";
		writeFile(from, to);
	}
	
	public static void writeFile(String from, String to) throws IOException
	{
		FileReader reader = new FileReader(new File(from));
		BufferedReader br = new BufferedReader(reader);
		
		PrintWriter bw = new PrintWriter(new FileWriter(new File(to)));
		
		int s;
		
		while((s=br.read()) != -1)
		{
			System.out.println(s);
			bw.write((char)s);
		}
		
		bw.close();
		
		
		
	}

}
