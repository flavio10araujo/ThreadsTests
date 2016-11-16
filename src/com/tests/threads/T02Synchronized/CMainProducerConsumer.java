package com.tests.threads.T02Synchronized;

import java.util.Random;

/**
 * The producer/consumer is a typical thread synchronization problem that uses the wait() and notify() methods.
 * An object of the Buffer class has an integer data element that will be produced by the producer and consumed by the consumer.
 * We have to synchronize the access to the buffer, so the Producer produces a new data element only when the Buffer is empty and the Consumer consumes the buffer's data only when it is available.
 * 
 * In order to work correctly, the thread that calls the wait() method has to hold a lock that it has acquired before using the synchronized keyword. 
 * When calling wait() the lock is released and the threads waits until another thread that now owns the lock calls notify() on the same object instance.
 */
public class CMainProducerConsumer {
	
	public static void main(String[] args) {
		
		Buffer buffer = new Buffer();
		
		Producer p = new Producer(buffer);
		
		Consumer c = new Consumer(buffer);

		p.start();
		c.start();
	}
}

class Producer extends Thread {
	
	private Buffer buffer;

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		Random rand = new Random();
		while (true) {
			int n = rand.nextInt();
			buffer.produce(n);
		}
	}
}

class Consumer extends Thread {

	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		int data;
		while (true) {
			data = buffer.consume();
		}
	}
}

class Buffer {
	
	private int data;
	private boolean empty;

	public Buffer() {
		this.empty = true;
	}

	public synchronized void produce(int newData) {
		
		while (!this.empty) {
			try {
				this.wait(); // Se o buffer não está vazio, a thread aguarda e libera o lock.
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data = newData;
		this.empty = false;
		this.notify(); // Se o buffer estava vazio e esta thread colocou um conteúdo nele, então esta thread chama o método notify para avisar a outra thread.
		System.out.println("Produced: " + newData);
	}

	public synchronized int consume() {
		while (this.empty) {
			try {
				this.wait(); // Se o buffer estava vazio, a thread aguarda e libera o lock.
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.empty = true;
		this.notify(); // Se o buffer não estava vazio e esta thread tirou o conteúdo dele, então esta thread chama o método notify para avisar a outra thread.
		System.out.println("Consumed: " + data);
		return data;
	}
}