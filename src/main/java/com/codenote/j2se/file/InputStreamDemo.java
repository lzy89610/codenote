package com.codenote.j2se.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import scala.util.control.Exception.Finally;

public class InputStreamDemo
{

	public static void main(String[] args) throws IOException
	{
		String from = "tmp/from.txt";
		String to = "tmp/to.txt";
		
		copyFile(from, to);
	}
	
	/**
	 * 复制文件
	 * @param from
	 * @param to
	 * @throws IOException
	 */
	public static void copyFile(String from, String to) throws IOException
	{
		FileInputStream is = null;
		FileOutputStream fs = null;
		int readCnt = 0;
		byte[] buf = new byte[1024];
		
		try
		{
			is = new FileInputStream(new File(from));
			fs = new FileOutputStream(new File(to));
			
			//is.read(buf)的意思是将流中的数据读到buf里
			while((readCnt=is.read(buf)) != -1)
			{
				//fs.write(buf, 0, readCnt)的意思是将buf中从起始位置为0的地方写readCnt个字节到指定文件中
				fs.write(buf, 0, readCnt);
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (fs != null)
				{
					fs.close();
				}
				if(is != null)
				{
					is.close();
				}
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
			
		}
		
	}

}
