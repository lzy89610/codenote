package com.codenote.j2se.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class test
{

	public static void main(String[] args) throws IOException
	{
		String to = "tmp/to.txt";
		
		writeFile(to, "hello world how are you?");
		

	}
	
	public static void writeFile(String to, String content) throws IOException
	{
//		FileWriter fw = new FileWriter(new File(to));
		
		FileOutputStream fs = new FileOutputStream(new File(to));
		PrintWriter pw = new PrintWriter(fs);
		pw.print(content);
		pw.close();
		fs.close();
		
		
	}

}
