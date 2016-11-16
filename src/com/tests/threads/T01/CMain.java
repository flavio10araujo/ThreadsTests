package com.tests.threads.T01;

import java.lang.Thread.State;

public class CMain {
	
	public static void main(String[] args) {
		
		long id = Thread.currentThread().getId();
		
		String name = Thread.currentThread().getName();
		
		int priority = Thread.currentThread().getPriority();
		
		State state = Thread.currentThread().getState();
		
		String threadGroupName = Thread.currentThread().getThreadGroup().getName();
		
		// id=1; name=main; priority=5; state=RUNNABLE; threadGroupName=main
		System.out.println("id="+id+"; name="+name+"; priority="+priority+"; state="+state+"; threadGroupName="+threadGroupName);
	}
}