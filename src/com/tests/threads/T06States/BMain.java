package com.tests.threads.T06States;

public class BMain extends Thread {
	
	private volatile boolean keepRunning = true;
	private boolean suspended = false;
	
	public synchronized void stopThread() {
		this.keepRunning = false;
		this.notify();
	}

	public synchronized void suspendThread() {
		this.suspended = true;
	}

	public synchronized void resumeThread() {
		this.suspended = false;
		this.notify();
	}

	public void run() {
		System.out.println("Thread started...");
		
		while (keepRunning) {
			try {
				System.out.println("Going to sleep...");
				Thread.sleep(1000);
				
				synchronized (this) {
					while (suspended) {
						System.out.println("Suspended...");
						this.wait();
						System.out.println("Resumed...");
					}
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BMain t = new BMain();
		t.start();
		Thread.sleep(2000);
		t.suspendThread();
		Thread.sleep(2000);
		t.resumeThread();
		Thread.sleep(2000);
		t.stopThread();
	}
}