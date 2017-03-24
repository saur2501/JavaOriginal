package javaConcepts.self;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable {

	private String myName;
	private int count;
	private final long timeSleep;

	MyThread(String name, int newcount, long newtimeSleep) {
		this.myName = name;
		this.count = newcount;
		this.timeSleep = newtimeSleep;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		int sum = 0;
		for (int i = 1; i <= this.count; i++) {
			sum = sum + i;
		}
		try {
			Thread.sleep(this.timeSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myName + " thread has sum = " + sum + " and is going to sleep for " + timeSleep);

	}

}

public class Multithreading {

	private static Future taskTwo = null;
	private static Future taskThree = null;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("1. Invoking call method");
		ExecutorService executor = Executors.newFixedThreadPool(2);

		// execute the Runnable
		Runnable taskOne = new MyThread("TaskOne", 2, 100);
		executor.execute(taskOne); // executes run method
		for (int i = 0; i < 2; i++) {
			// if this task is not created or is canceled or is completed
			if ((taskTwo == null) || (taskTwo.isDone()) || (taskTwo.isCancelled())) {
				// submit a task and return a Future
				taskTwo = executor.submit(new MyThread("TaskTwo", 4, 200));
				System.out.println("Gods be good");
			}

			if ((taskThree == null) || (taskThree.isDone()) || (taskThree.isCancelled())) {
				taskThree = executor.submit(new MyThread("TaskThree", 5, 100));
			}
			// if null the task has finished
			if (taskTwo.get() == null) {
				System.out.println(i + 1 + ") TaskTwo terminated successfully");
			} else {
				// if it doesn't finished, cancel it
				taskTwo.cancel(true);
			}
			if (taskThree.get() == null) {
				System.out.println(i + 1 + ") TaskThree terminated successfully");
			} else {
				taskThree.cancel(true);
			}
		}
		executor.shutdown();
		System.out.println("-----------------------");
		// wait until all tasks are finished
		executor.awaitTermination(1, TimeUnit.SECONDS);
		System.out.println("All tasks are finished!");
		System.out.println("2. Starting Callable Example code");
		try {
			CallableExample.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class CallableExample {

	public static class WordLengthCallable implements Callable {
		private String word;

		public WordLengthCallable(String word) {
			this.word = word;
		}

		public Integer call() {
			return Integer.valueOf(word.length());
		}
	}

	public static void execute() throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Set<Future<Integer>> set = new HashSet<Future<Integer>>();
		String[] stringList = { "Krishna", "Gauranga", "Narsimha" };
		for (String word : stringList) {
			Callable<Integer> callable = new WordLengthCallable(word);
			Future<Integer> future = pool.submit(callable);
			set.add(future);
		}
		int sum = 0;
		for (Future<Integer> future : set) {
			sum += future.get();
		}
		System.out.printf("The sum of lengths is %s%n", sum);
		System.out.println("Calling Hello World Code");
		HelloWorld1.execute();
		System.exit(sum);
	}
}

// one class needs to have a main() method
class HelloWorld1 extends Thread {

	static Semaphore mutex = new Semaphore(1);

	synchronized public void run() {
		try {
			mutex.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// OtherClass other = new OtherClass("Hi");
		System.out.println(Thread.currentThread().getName());
		// other.synch();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		System.out.println(Thread.currentThread().getName());
		// System.out.println("thread is running...");
		mutex.release();
	}

	public static void execute() throws Exception {
		HelloWorld1 t1 = new HelloWorld1();
		HelloWorld1 t2 = new HelloWorld1();
		HelloWorld1 t3 = new HelloWorld1();
		HelloWorld1 t4 = new HelloWorld1();
		HelloWorld1 t5 = new HelloWorld1();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}

// you can add other public classes to this editor in any order
class OtherClass {
	private String message;
	private boolean answer = false;

	public OtherClass(String input) {
		message = "Why, " + input + " Isn't this something?";
	}

	public String toString() {
		return message;
	}

	public String synch() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		return "Tweet";
	}
}