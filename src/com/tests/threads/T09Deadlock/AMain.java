package com.tests.threads.T09Deadlock;

import java.util.Random;

/**
 * Example of deadlock.
 * 
 * If we would set the boolean variable b in the example code above to true, we would not experience any deadlock because 
 * the sequence in which thread-1 and thread-2 are requesting the locks is always the same. 
 * Hence one of both threads gets the lock first and then requests the second lock, which is still available because the 
 * other threads wait for the first lock.
 */
public class AMain implements Runnable {
	
	private static final Object resource1 = new Object();
	
	private static final Object resource2 = new Object();
	
	private final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		
		Thread myThread1 = new Thread(new AMain(), "thread-1");
		
		Thread myThread2 = new Thread(new AMain(), "thread-2");
		
		myThread1.start();
		
		myThread2.start();
	}

	public void run() {
		
		for (int i = 0; i < 10000; i++) {
			
			boolean b = random.nextBoolean();
			
			if (b) {
				System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 1.");
				
				synchronized (resource1) {
					
					System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 1.");
					
					System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 2.");
					
					synchronized (resource2) {
						System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 2.");
					}
				}
			}
			else {
				
				System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 2.");
				
				synchronized (resource2) {
					
					System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 2.");
					
					System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 1.");
					
					synchronized (resource1) {
						System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 1.");
					}
				}
			}
		}
	}
}