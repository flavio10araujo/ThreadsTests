package com.tests.threads.T02Synchronized;

/**
 * Output (it can vary):
 * ...
 * [thread-2] before: 8
 * [thread-2] after: 9
 * [thread-1] before: 0
 * [thread-1] after: 10
 * [thread-2] before: 9
 * [thread-2] after: 11
 */
public class CounterNotSynchronized implements Runnable {
	
	private static int counter = 0;

	public void run() {
		while (counter < 10) {
			System.out.println("["+Thread.currentThread().getName()+"] before: "+counter);
			counter++;
			System.out.println("["+Thread.currentThread().getName()+"] after: "+counter);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		Thread[] threads = new Thread[5];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new CounterNotSynchronized(), "thread-"+i);
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
}