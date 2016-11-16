package com.tests.threads.T02Synchronized;

/**
 * Esse c�digo N�O usa o synchronized, ent�o o resultado ser� o print de:
 * end: 1
 * start: 1
 * Balance changed: 2
 * Pode demorar um tempo pra acontecer, mas vai acontecer.
 */
public class BMainNotSynchronized {

	private static int myValue = 1;

	public static void main(String[] args) {
		
		Thread t = new Thread(() -> {
			while (true) {
				updateBalance();
			}
		});
		
		t.start();
		
		Thread t2 = new Thread(() -> {
			while (true) {
				monitorBalance(2);
			}
		});
		
		t2.start();
		
		Thread t3 = new Thread(() -> {
			while (true) {
				monitorBalance(3);
			}
		});
		
		t3.start();
		
		Thread t4 = new Thread(() -> {
			while (true) {
				monitorBalance(4);
			}
		});
		
		t4.start();
		
		Thread t5 = new Thread(() -> {
			while (true) {
				monitorBalance(5);
			}
		});
		
		t5.start();
	}

	public static void updateBalance() {
		System.out.println("start: " + myValue);
		myValue = myValue + 1;
		
		// Coloquei o sleep apenas para terminar mais r�pido.
		// Pois esse m�todo � muito pequeno, ent�o � dif�cil de acontecer de uma outra thread pegar o valor do myValue igual a 2.
		// Colocando o sleep, forcei isso acontecer.
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myValue = myValue - 1;
		System.out.println("end: " + myValue);
	}

	public static void monitorBalance(int id) {
		System.out.println("Thread " + id + " verificando...");
		
		int b = myValue;
		
		if (b != 1) {
			System.out.println("Balance changed: " + b);
			System.exit(1); 
		}
	}
}