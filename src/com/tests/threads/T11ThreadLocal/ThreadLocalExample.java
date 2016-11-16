package com.tests.threads.T11ThreadLocal;

/**
 * Dedicated memory that is only used by our own thread and not by other threads is provided in Java through the java.lang.ThreadLocal class.
 *
 * In this example, you may wonder that each thread outputs exactly the value that it gets through the constructor although 
 * the variable threadLocal is declared static.
 * 
 * ThreadLocal’s internal implementation makes sure that every time you call set() the given value is stored within a memory 
 * region that only the current thread has access to.
 * 
 * Hence when you call afterwards get() you retrieve the value you have set before, despite the fact that in the meantime 
 * other threads may have called set().
 */
public class ThreadLocalExample implements Runnable {
	
	private static final ThreadLocal threadLocal = new ThreadLocal();
	
	private final int value;

	public ThreadLocalExample(int value) {
		this.value = value;
	}

	@Override
	public void run() {
		threadLocal.set(value);
		Integer integer = (Integer) threadLocal.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: " + integer);
	}

	public static void main(String[] args) throws InterruptedException {
		
		Thread threads[] = new Thread[5];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new ThreadLocalExample(i), "thread-" + i);
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
}