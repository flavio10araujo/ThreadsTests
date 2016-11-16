package com.tests.threads.T02Synchronized;

/**
 * The synchronized keyword is used to declare a critical section which need synchronization.
 * There are two ways to use the synchronized keyword:
 * To declare a method as a critical section;
 * To declare a block of statements as a critical section.
 */
public class AMain {

	public synchronized void someMethod_1() {
		// Only one thread can execute here at a time.
	}

	public void someMethod_11() {
		// Multiple threads can execute here at a time.

		synchronized(this) {
			// Only one thread can execute here at a time.
		}

		// Multiple threads can execute here at a time.
	}

	public void someMethod_12() {
		// Multiple threads can execute here at a time.

		synchronized(this) {
			// Only one thread can execute here at a time.
		}

		// Multiple threads can execute here at a time.
	}

	public static synchronized void someMethod_2() {
		// Only one thread can execute here at a time.
	}

	public static void someMethod_21() {
		synchronized(AMain.class) {
			// Only one thread can execute here at a time.
		}
	}

	public static void someMethod_22() {
		// Multiple threads can execute here at a time
		
		synchronized(AMain.class) {
			// Only one thread can execute here at a time.
		}
		
		// Multiple threads can execute here at a time.
	}
}