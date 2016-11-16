package com.tests.threads.T05Join;

/**
 * O resultado desse código é:
 * Done.
 * Counter: 1
 * Counter: 2
 * Counter: 3
 * Counter: 4
 * Counter: 5
 * O que não é o ideal, pois o Done só deveria aparecer após o Counter: 5.
 */
public class AMainWithoutJoin {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(AMainWithoutJoin::print);
		t1.start();
		System.out.println("Done.");
	}

	public static void print() {
		for (int i = 1; i <= 5; i++) {
			try {
				System.out.println("Counter: " + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}