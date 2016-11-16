package com.tests.threads.T05Join;

/**
 * O resultado desse c�digo �:
 * Counter: 1
 * Counter: 2
 * Counter: 3
 * Counter: 4
 * Counter: 5
 * Done.
 * Isso ocorre porque a thread main est� chamando a m�todo join() da thread t1.
 * Dessa forma, a main espera o fim da execu��o da t1.
 */
public class AMainWithJoin {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(AMainWithJoin::print);
		t1.start();
		
		try {
			t1.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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