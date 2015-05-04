package com.codenote.j2se.cglib;

public class Main
{

	public static void main(String[] args)
	{
		HelloSpeakerProxy proxy = new HelloSpeakerProxy();
		HelloSpeaker speaker = (HelloSpeaker) proxy.getProxy(HelloSpeaker.class);
		speaker.sayHello("hello world");
	}
	
}
