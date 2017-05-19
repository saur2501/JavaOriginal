package system.os;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ProducerConsumerProblem {
	static LinkedList<String> crunchifyList = new LinkedList<String>();

	static Semaphore full = new Semaphore(0);
	static Semaphore empty = new Semaphore(10);
	static Semaphore mutex = new Semaphore(1);

	static class Producer extends Thread {
		public void run() {

			int counter = 1;
			try {
				while (true) {
					empty.acquire();
					String threadName = Thread.currentThread().getName() + counter++;
					mutex.acquire();
					crunchifyList.add(threadName);
					System.out.println("Producer is producing new value: " + threadName);
					System.out.println("Producer observes list state as " + Arrays.toString(crunchifyList.toArray()));
					mutex.release();
					full.release();
					Thread.sleep(500);
				}
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
	}

	static class Consumer extends Thread {
		String consumerName;

		public Consumer(String name) {
			this.consumerName = name;
		}

		public void run() {
			try {

				while (true) {
					full.acquire();
					mutex.acquire();
					System.out.println("Consumer consumes " + crunchifyList.poll() + " List Size = " + crunchifyList.size()); 	//replacing poll by remove should not throw exception
					System.out.println("Consumer observes list state as " + Arrays.toString(crunchifyList.toArray()));
					mutex.release();
					empty.release();
					//Thread.sleep(500);		//decide whether to slow consumer or producer- to see effects
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Producer().start();
		Thread.sleep(3000);
		new Consumer("Consumer").start();
		// new CrunchifyConsumer("Google").start();
		// new CrunchifyConsumer("Yahoo").start();
	}
}