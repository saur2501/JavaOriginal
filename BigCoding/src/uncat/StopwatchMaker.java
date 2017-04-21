package uncat;

import org.apache.commons.lang3.time.StopWatch;

public class StopwatchMaker {
	public static void main(String[] args) throws InterruptedException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		System.out.println(stopWatch.getTime());
		stopWatch.suspend();
        Thread.sleep(2000);
        stopWatch.resume();
        Thread.sleep(2000);
        stopWatch.stop();
		System.out.println(stopWatch.getTime());
		stopWatch.reset();
        stopWatch.start();
		System.out.println(stopWatch.getTime());
	}
}
