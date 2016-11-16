package com.tests.threads.T01;

/**
 * There are three ways you can specify your code to be executed by a thread:
 * 1 - By inheriting your class from the Thread class;
 * 2 - By implementing the Runnable interface in your class;
 * 3 - Java 8: By using the method reference to a method that takes no parameters and returns void.
 * 
 * 
 * Whether you should use the subclassing or the interface approach, depends to some extend on your taste.
 * The interface is a more light-weight approach as all you have to do is the implementation of an interface.
 * The class can still be a subclass of some other class.
 * You can also pass your own parameters to the constructor whereas subclassing Thread restricts you to the available constructors that the class Thread brings along.
 * 
 */
public class AMain {

	public static void main(String[] args) {
		// 1
		MyThreadClass myThread = new MyThreadClass();
		myThread.start();
		
		// 2
		Runnable aRunnableObject = () -> System.out.println("2 - Hello Java thread!");
		Thread myThread2 = new Thread(aRunnableObject);
		myThread2.start();
		
		// 2 (without lambdas)
		Thread myThread2b = new Thread(new MyRunnable(), "myRunnable");
		myThread2b.start();
		
		// 3
		Thread myThread3 = new Thread(ThreadTest::execute);
		myThread3.start();
	}
}

// 1 - By inheriting your class from the Thread class; 
class MyThreadClass extends Thread {
	@Override
	public void run() {
		System.out.println("1 - Hello Java thread!");
	}
}

class MyRunnable implements Runnable {
	public void run() {
		System.out.println("2b - Hello Java thread!");
	}
}

class ThreadTest {
	public static void execute() {
		System.out.println("3 - Hello Java thread!");
	}
}