package com.tests.threads.T02Synchronized;

import java.util.Random;

/**
 * The producer/consumer is a typical thread synchronization problem that uses the wait() and notify() methods.
 * An object of the Buffer class has an integer data element that will be produced by the producer and consumed by the consumer.
 * We have to synchronize the access to the buffer, so the Producer produces a new data element only when the Buffer is empty and the Consumer consumes the buffer's data only when it is available.
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
				this.wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data = newData;
		this.empty = false;
		this.notify();
		System.out.println("Produced: " + newData);
	}

	public synchronized int consume() {
		while (this.empty) {
			try {
				this.wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.empty = true;
		this.notify();
		System.out.println("Consumed: " + data);
		return data;
	}
}