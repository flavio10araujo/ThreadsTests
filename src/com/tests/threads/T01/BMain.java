package com.tests.threads.T01;

// The following code shows how to run Multiple Threads in a Program.
public class BMain {

	public static void main(String[] args) {
		// Create two Thread objects.
		Thread t1 = new Thread(BMain::print);
		Thread t2 = new Thread(BMain::print);
		
		// Start both threads.
		t1.start();
		t2.start();
	}

	public static void print() {
		for (int i = 1; i <= 500; i++) {
			System.out.println(i);
		}
	}
}