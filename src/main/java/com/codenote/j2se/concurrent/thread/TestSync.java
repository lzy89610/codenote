package com.codenote.j2se.concurrent.thread; 

/**
 * 演示synchronized的效果
 * 有两个线程t1,t2，它们共用一个对象timer
 * 在timer中有一个表示状态的变量num，是线程非安全的。当调用add方法时，必须加上同步锁
 * @author lizeyu
 */
public class TestSync implements Runnable
{
	Timer timer = new Timer();

	public static void main(String[] args)
	{
		TestSync test = new TestSync();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}

	public void run()
	{
		timer.add(Thread.currentThread().getName());
	}
}

class Timer
{
	private static int num = 0;

	public void add(String name)
	{
		synchronized (this)
		{
			num++;
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
			}
			System.out.println(name + ", 你是第" + num + "个使用timer的线程");
		}
	}
}