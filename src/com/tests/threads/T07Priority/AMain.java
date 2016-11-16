package com.tests.threads.T07Priority;

/**
 * main Thread Priority: 5
 * Thread(t1) Priority: 5
 * main Thread Priority: 10
 * Thread(t2) Priority: 10 // Interessante observar que a prioridade da thread é a mesma da thread que a criou.
 * Thread(t2) Priority: 1
 */
public class AMain {
	
	public static void main(String[] args) {
		
		Thread t = Thread.currentThread();
		
		System.out.println("main Thread Priority: " + t.getPriority());

		Thread t1 = new Thread();
		
		System.out.println("Thread(t1) Priority: " + t1.getPriority());

		t.setPriority(Thread.MAX_PRIORITY);
		
		System.out.println("main Thread Priority: " + t.getPriority());

		Thread t2 = new Thread();
		
		System.out.println("Thread(t2) Priority: " + t2.getPriority());

		// Change thread t2 priority to minimum.
		t2.setPriority(Thread.MIN_PRIORITY);
		
		System.out.println("Thread(t2) Priority: " + t2.getPriority());
	}
}