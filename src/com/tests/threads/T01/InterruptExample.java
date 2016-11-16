package com.tests.threads.T01;

/**
 * Input:
 * 1 - [main] Sleeping in main thread for 5s...
 * 2 - [main] Interrupting myThread
 * 3 - [main] Sleeping in main thread for 5s...
 * 4 - [myThread] Interrupted by exception!
 * 5 - [main] Interrupting myThread
 * 6 - [myThread] Interrupted for the second time.
 * 
 * What is interesting in this output, are the lines 3 and 4. 
 * If we go through the code we might have expected that the string “Interrupted by exception!” is printed out before the main thread starts sleeping again with “Sleeping in main thread for 5s”. 
 * But as you can see from the output, the scheduler has executed the main thread before it started myThread again. 
 * Hence myThread prints out the reception of the exception after the main thread has started sleeping.
 */
public class InterruptExample implements Runnable {

	public void run() {
		try {
			Thread.sleep(Long.MAX_VALUE);
		}
		catch (InterruptedException e) {
			System.out.println("["+Thread.currentThread().getName()+"] Interrupted by exception!");
		}
		
		while(!Thread.interrupted()) {
			// do nothing here
		}
		
		System.out.println("["+Thread.currentThread().getName()+"] Interrupted for the second time.");
	}

	public static void main(String[] args) throws InterruptedException {
		Thread myThread = new Thread(new InterruptExample(), "myThread");
		
		myThread.start();
		
		System.out.println("["+Thread.currentThread().getName()+"] Sleeping in main thread for 5s...");
		Thread.sleep(5000);
		
		System.out.println("["+Thread.currentThread().getName()+"] Interrupting myThread");
		myThread.interrupt();
		
		System.out.println("["+Thread.currentThread().getName()+"] Sleeping in main thread for 5s...");
		Thread.sleep(5000);
		
		System.out.println("["+Thread.currentThread().getName()+"] Interrupting myThread");
		myThread.interrupt();
	}
}