package com.tests.threads.T02Synchronized;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * In this example we have accidentally added the synchronized keyword to the method getNextInt and afterwards synchronized on the object monitor queue 
 * in order to put the current thread into sleep while waiting for the next value from the queue.
 * The current thread then releases the lock hold on queue but not the lock hold on this.
 * 
 * The putInt() method notifies the sleeping thread that a new value has been added.
 * But accidentally we have also added the keyword synchronized to this method.
 * When now the second thread has fallen asleep, it still holds the lock.
 * 
 * The first thread can then not enter the method putInt() as the this lock is hold by the first thread.
 * 
 * Hence we have a deadlock situation and the program hangs.
 */
public class SynchronizedAndWait {
	
	private static final Queue queue = new ConcurrentLinkedQueue();

	public synchronized Integer getNextInt() {
		
		Integer retVal = null;
		
		while (retVal == null) {
			
			synchronized (queue) {
				try {
					queue.wait();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				retVal = (Integer) queue.poll();
			}
		}
		
		return retVal;
	}

	public synchronized void putInt(Integer value) {
		synchronized (queue) {
			queue.add(value);
			queue.notify();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		final SynchronizedAndWait queue = new SynchronizedAndWait();
		
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					queue.putInt(i);
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					Integer nextInt = queue.getNextInt();
					System.out.println("Next int: " + nextInt);
				}
			}
		});
		
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}
}