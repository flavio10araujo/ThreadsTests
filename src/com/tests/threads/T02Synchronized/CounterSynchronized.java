package com.tests.threads.T02Synchronized;

/**
 * Output (it can vary):
 * [thread-0] before: 0
 * [thread-0] after: 1
 * [thread-0] before: 1
 * [thread-0] after: 2
 * [thread-1] before: 2
 * [thread-1] after: 3
 * [thread-1] before: 3
 * [thread-1] after: 4
 * [thread-1] before: 4
 * [thread-1] after: 5
 * [thread-1] before: 5
 * [thread-1] after: 6
 * [thread-1] before: 6
 * [thread-1] after: 7
 * [thread-1] before: 7
 * [thread-1] after: 8
 * [thread-1] before: 8
 * [thread-1] after: 9
 * thread-1] before: 9
 * [thread-1] after: 10
 * [thread-2] before: 10
 * [thread-2] after: 11
 * [thread-0] before: 11
 * [thread-0] after: 12
 * 
 * There is not the possibility of occur "[thread-1] before: 0" followed by "[thread-1] after: 10", for example.
 */
public class CounterSynchronized implements Runnable {

	private static int counter = 0;

	public void run() {
		while (counter < 10) {
			synchronized (CounterSynchronized.class) {
				System.out.println("[" + Thread.currentThread().getName() + "] before: " + counter);
				counter++;
				System.out.println("[" + Thread.currentThread().getName() + "] after: " + counter);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		Thread[] threads = new Thread[5];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new CounterSynchronized(), "thread-"+i);
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
}