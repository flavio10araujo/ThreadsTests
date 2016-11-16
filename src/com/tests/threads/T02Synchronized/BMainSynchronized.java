package com.tests.threads.T02Synchronized;

/**
 * Esse c�digo usa o synchronized, ent�o o resultado ser� o print eterno de:
 * end: 1
 * start: 1
 * ...
 * Isso s� ocorre porque os dois m�todos est�o marcados com o synchronized. 
 * Se eu tirar de um dos m�todos, n�o funciona mais.
 * 
 * Portanto, para assegurar o acesso �nico � vari�vel myValue � necess�rio implementar synchronized em todos os m�todos
 * que fazem leitura e/ou escrita nessa vari�vel.
 */
public class BMainSynchronized {
	
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

	public static synchronized void updateBalance() {
		System.out.println("start: " + myValue);
		myValue = myValue + 1;
		
		// Mesmo se eu coloco um sleep de 5 segundos, n�o quebra a sincronia.
		// Ou seja, eternamente o valor de myValue vai ser lido como 1 pelo m�todo monitorBalance.
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		myValue = myValue - 1;
		System.out.println("end: " + myValue);
	}

	public static synchronized void monitorBalance(int id) {
		System.out.println("Thread " + id + " verificando...");
		
		int b = myValue;
		if (b != 1) {
			System.out.println("Balance changed: " + b);
			System.exit(1); 
		}
	}
}