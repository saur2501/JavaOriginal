package uncat;

import com.codahale.metrics.*;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.util.Queue;

public class MetricsMeasurements {
	static final MetricRegistry metrics = new MetricRegistry();
	static Queue queue = new Queue();

	public static void main(String args[]) {
		startReport(); // configures for console printing at interval

		Meter meter = metrics.meter("meter"); // what name it comes for
		meter.mark();
		meter.mark(3); // Number shows on measurement
		Counter counter = metrics.counter("Counter");
		counter.inc();
		Histogram histogram = metrics.histogram("Histogram");
		histogram.update(5);
		Timer timer = metrics.timer("Timer");
		timer.update(2L, TimeUnit.MILLISECONDS);
		timer.time();
		queue.put(1);
		metrics.register(MetricRegistry.name(MetricsMeasurements.class, "Gauge"), new Gauge<Integer>() {
			public Integer getValue() {
				return queue.size();
			}
		});
		wait5Seconds();
	}

	static void startReport() {
		ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
		reporter.start(1, TimeUnit.SECONDS);
	}

	static void wait5Seconds() {
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}
	}
}
//ref- https://www.youtube.com/watch?v=czes-oa0yik
//tuts- http://metrics.dropwizard.io/3.1.0/getting-started/