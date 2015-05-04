package com.codenote.j2se.concurrent.cylicbarriar;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class HelloSpeaker implements Runnable
{
	private CyclicBarrier barrier;
	
	private String name;
	
	public HelloSpeaker(CyclicBarrier barrier, String name)
	{
		super();
		this.barrier = barrier;
		this.name = name;
	}

	public void run()
	{
		System.out.println(name + " ok");
		try
		{
			barrier.await();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (BrokenBarrierException e)
		{
			e.printStackTrace();
		}
		System.out.println(name + " go");
		
	}
	
	
}
