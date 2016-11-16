package com.tests.threads.T08Volatile;

public class AMain extends Thread {
	
	private volatile boolean keepRunning = true;
	
	public void run() {
		System.out.println("Thread started.");
		
		while (keepRunning) {
			try {
				System.out.println("Going to sleep.");
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Thread stopped.");
	}
	
	public void stopThread() {
		this.keepRunning = false;
	}
	
	public static void main(String[] args) throws Exception {
		AMain v = new AMain();
		v.start();
		Thread.sleep(3000);
		System.out.println("Going to set the stop flag to true.");
		v.stopThread();
	}
}