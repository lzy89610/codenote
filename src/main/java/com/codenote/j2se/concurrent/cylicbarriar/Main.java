package com.codenote.j2se.concurrent.cylicbarriar;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
	public static void main(String[] args)
	{
		CyclicBarrier barrier = new CyclicBarrier(3);  
		  
        ExecutorService executor = Executors.newFixedThreadPool(3);  
        executor.submit(new Thread(new HelloSpeaker(barrier, "zhangsan")));  
        executor.submit(new Thread(new HelloSpeaker(barrier, "lisi")));  
        executor.submit(new Thread(new HelloSpeaker(barrier, "wangwu")));  
  
        executor.shutdown();  
		
		
	}
}
