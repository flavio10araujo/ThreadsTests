package com.tests.threads.T03CurrentThread;

public class AMain extends Thread {
	
	public AMain(String name) {
		super(name);
	}

	@Override
	public void run() {
		Thread t = Thread.currentThread(); // It returns the reference of the Thread object that calls this method.
		String threadName = t.getName();
		System.out.println("Inside run() method: " + threadName);
	}

	public static void main(String[] args) {
		AMain ct1 = new AMain("First Thread");
		AMain ct2 = new AMain("Second Thread");
		
		ct1.start();
		ct2.start();

		Thread t = Thread.currentThread(); // Pegando qual é a thread da main.
		String threadName = t.getName();  // Pegando o nome da thread da main.
		
		System.out.println("Inside main() method: " + threadName);
		
		// Resultado:
		// Inside main() method: main
		// Inside run() method: Second Thread
		// Inside run() method: First Thread
	}
}