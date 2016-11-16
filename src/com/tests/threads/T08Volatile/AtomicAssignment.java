package com.tests.threads.T08Volatile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * You can run the program for some time and you will not see any IllegalStateException. 
 * This is due the fact that we assign the new Map to the shared configuration variable in one atomic operation.
 * 
 * If you change the run() method in a way that it uses the configuration variable directly instead of copying it first to a local variable, 
 * you will see IllegalStateExceptions very soon because the configuration variable always points to the “current” configuration.
 * 
 * The same is true if you work in the readConfig() method directly on the configuration variable instead of creating a 
 * new Map and assigning it in one atomic operation to the shared variable.
 */
public class AtomicAssignment implements Runnable {
	
	private static volatile Map<String, String> configuration = new HashMap<String, String>();

	public void run() {
		
		for (int i = 0; i < 10000; i++) {
			
			Map<String, String> currConfig = configuration;
			
			String value1 = currConfig.get("key-1");
			
			String value2 = currConfig.get("key-2");
			
			String value3 = currConfig.get("key-3");
			
			if (!(value1.equals(value2) && value2.equals(value3))) {
				throw new IllegalStateException("Values are not equal.");
			}
			
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void readConfig() {
		
		Map<String, String> newConfig = new HashMap<String, String>();
		
		Date now = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		
		newConfig.put("key-1", sdf.format(now));
		
		newConfig.put("key-2", sdf.format(now));
		
		newConfig.put("key-3", sdf.format(now));
		
		configuration = newConfig;
	}

	public static void main(String[] args) throws InterruptedException {
		
		readConfig();
		
		Thread configThread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					
					readConfig();
					
					try {
						Thread.sleep(10);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "configuration-thread");
		
		configThread.start();
		
		Thread[] threads = new Thread[5];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new AtomicAssignment(), "thread-" + i);
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		configThread.join();
		
		System.out.println("[" + Thread.currentThread().getName() + "] All threads have finished.");
	}
}