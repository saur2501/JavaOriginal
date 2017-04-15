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

class ThreadExecutionForPool implements Runnable {

	private String myName;
	private int count;
	private final long timeSleep;

	ThreadExecutionForPool(String name, int newcount, long newtimeSleep) {
		this.myName = name;
		this.count = newcount;
		this.timeSleep = newtimeSleep;
	}

	@Override
	public void run() {
		int sum = 0;
		for (int i = 1; i <= this.count; i++) {
			sum = sum + i;
		}
		try {
			Thread.sleep(this.timeSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(myName + " thread has sum = " + sum + " and is going to sleep for " + timeSleep);
	}
}

class OtherClass {
	private String message;

	public OtherClass(String input) {
		message = "Why, " + input + " Isn't this something?";
	}

	public String toString() {
		return message;
	}

	public String synch() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
		return "Tweet";
	}

	public int updateVariable(int number) {
		System.out.println("Gosd");
		for(int i = 0; i < 1000; i++) {
			number++;
			System.out.println(number + "gotcha1");
		}
		System.out.println(number + "gotcha");
		return number;
	}
}

@SuppressWarnings("rawtypes")
public class Multithreading {

	public static void main(String[] args) throws Exception {
		// Thread pooling
		System.out.println("1. Running thru runnable");
		runThreadPool();
		System.out.println("2. Starting Callable Example code");
		int sum = callThreadPool();
		System.out.println("Sum is " + sum);
		System.out.println("3. Run method invocation");
		runInvocation();
		System.out.println("4. Thread safety");
		threadSafetyCheck();
		//System.exit(sum);
	}

	private static void threadSafetyCheck() {
		//is OtherClass' updateVariable method Thread-safe?
		Runnable runnableInline = new Runnable() {
			public void run() {
				OtherClass otherClass = new OtherClass("message");
				System.out.println("INline");
				System.out.println("Runnable Inline says: " + otherClass.updateVariable(5));
			}
		};
		Runnable runnableAnonymous = new Runnable() {
			public void run() {
				OtherClass otherClass = new OtherClass("message");
				System.out.println("Anony");
				System.out.println("Runnable Anonymous says: " + otherClass.updateVariable(6));
			}
		};
		System.out.println("toas");
		new Thread(runnableInline).start();
		System.out.println("Bisl");
		new Thread(runnableAnonymous).start();
	}

	private static void runInvocation() {
		ThreadExecutionRun t1 = new ThreadExecutionRun();
		ThreadExecutionRun t2 = new ThreadExecutionRun();
		ThreadExecutionRun t3 = new ThreadExecutionRun();
		t1.start();
		t2.start();
		t3.start();
	}

	private static int callThreadPool() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Set<Future<Integer>> set = new HashSet<Future<Integer>>();
		String[] stringList = { "Krishna", "Gauranga", "Narsimha" };
		for (String word : stringList) {
			Callable<Integer> callable = new WordLengthCallable(word);
			Future<Integer> future = executorService.submit(callable);
			set.add(future);
		}
		executorService.shutdown();					//absolutely must with executor service for program to stop after done
		//executorService.awaitTermination(1, TimeUnit.SECONDS);		//to pause the current thread to wait for all to finish
		int sum = 0;
		for (Future<Integer> future : set) {
			sum += future.get(); // invokes call() method
		}
		System.out.printf("The sum of lengths is %s%n", sum);
		return sum;
	}

	private static void runThreadPool() throws InterruptedException, ExecutionException {
		Future taskTwo = null;
		Future taskThree = null;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Runnable taskOne = new ThreadExecutionForPool("TaskOne", 2, 100);
		executorService.execute(taskOne); // executes run method
		for (int i = 0; i < 2; i++) {
			// if this task is not created or is canceled or is completed
			if ((taskTwo == null) || (taskTwo.isDone()) || (taskTwo.isCancelled())) {
				// submit a task and return a Future
				taskTwo = executorService.submit(new ThreadExecutionForPool("TaskTwo", 4, 200));
				System.out.println("Gods be good");
			}

			if ((taskThree == null) || (taskThree.isDone()) || (taskThree.isCancelled())) {
				taskThree = executorService.submit(new ThreadExecutionForPool("TaskThree", 5, 100));
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
		executorService.shutdown();
		System.out.println("-----------------------");
		executorService.awaitTermination(1, TimeUnit.SECONDS); 	// wait until all tasks are finished
		System.out.println("All tasks are finished!");
	}

}

class WordLengthCallable implements Callable<Integer> {
	private String word;

	public WordLengthCallable(String word) {
		this.word = word;
	}

	public Integer call() {
		return Integer.valueOf(word.length());
	}
}

class ThreadExecutionRun extends Thread {
	static Semaphore mutex = new Semaphore(1);

	synchronized public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " is waiting");
			mutex.acquire();
			OtherClass other = new OtherClass("Hi");
			System.out.println(Thread.currentThread().getName());
			other.synch();
			Thread.sleep(500);
			System.out.println(Thread.currentThread().getName());
			System.out.println("thread is running...");
			mutex.release();
			System.out.println(Thread.currentThread().getName() + " is done");
		} catch (InterruptedException ex) {
		}
	}
}

// add Thread.sleep and do following- 
// use ps -T aux to view all thread-id's spawned by processes.
// use process explorer for windows