package system.os;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class UnlimitedProducerBulkConsumerProblem {
    static Object crunchifyLock = new Object();
    static LinkedList<String> crunchifyList = new LinkedList<String>();
    
    // Semaphore maintains a set of permits.
    // Each acquire blocks if necessary until a permit is available, and then takes it.
    // Each release adds a permit, potentially releasing a blocking acquirer.
    static Semaphore synchronizeSemaphore = new Semaphore(0);
    static Semaphore listMutex = new Semaphore(1);
    
    // I'll producing new Integer every time
    static class CrunchifyProducer extends Thread {
        public void run() {
            
            int counter = 1;
            try {
                while (true) {
                    String threadName = Thread.currentThread().getName() + counter++;
                    
                    listMutex.acquire();
                    crunchifyList.add(threadName);
                    System.out.println("Producer is producing new value: " + threadName);
                    System.out.println("Producer observes list state as " + Arrays.toString(crunchifyList.toArray()));
                    listMutex.release();
                    
                    // release lock
                    synchronizeSemaphore.release();
                    Thread.sleep(500);
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
    
    // I'll be consuming Integer every stime
    static class CrunchifyConsumer extends Thread {
        String consumerName;
        
        public CrunchifyConsumer(String name) {
            this.consumerName = name;
        }
        
        public void run() {
            try {
                
                while (true) {
                    
                    // acquire lock. Acquires the given number of permits from this semaphore, blocking until all are
                    // available
                    // process stops here until producer releases the lock
                    synchronizeSemaphore.acquire();
                    
                    // Acquires a permit from this semaphore, blocking until one is available
                    listMutex.acquire();
                    String result = "";
                    System.out.println("Consumer observes list state as " + Arrays.toString(crunchifyList.toArray()));
                    for (String value : crunchifyList) {
                        result += value + ",";
                    }
                    System.out.println(consumerName + " consumes values: " + result + "crunchifyList.size(): "
                            + crunchifyList.size() + "\n");
                    crunchifyList.clear();
                    listMutex.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        new CrunchifyProducer().start();
        Thread.sleep(3000);
        new CrunchifyConsumer("Crunchify").start();
        new CrunchifyConsumer("Google").start();
        new CrunchifyConsumer("Yahoo").start();
    }
}