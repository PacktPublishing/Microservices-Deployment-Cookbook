package com.packt.microservices.geolocation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.jvm.ClassLoadingGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;

@Component
public class MetricSystem {

	@Autowired
	private MetricRegistry metricRegistry;

	private Counter geolocationWriteRequestCount;
	private Long geolocationLastWriteTime;

	@PostConstruct
	public void init() {
		//		ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry)
		//				.convertRatesTo(TimeUnit.SECONDS)
		//				.convertDurationsTo(TimeUnit.MILLISECONDS)
		//				.build();
		//
		//		consoleReporter.start(10, TimeUnit.SECONDS);

//		Graphite graphite = new Graphite(new InetSocketAddress("192.168.99.100", 2003));
//		GraphiteReporter graphiteReporter = GraphiteReporter.forRegistry(metricRegistry)
//				.prefixedWith("com.packt.microservices.geolocation")
//				.convertRatesTo(TimeUnit.SECONDS)
//				.convertDurationsTo(TimeUnit.MILLISECONDS)
//				.filter(MetricFilter.ALL)
//				.build(graphite);
//		graphiteReporter.start(60, TimeUnit.SECONDS);


		geolocationWriteRequestCount = metricRegistry.counter("geolocationWriteRequestCount");
		metricRegistry.register("geolocationLastWriteTime", new Gauge<Long>() {
			@Override
			public Long getValue() {
				return geolocationLastWriteTime;
			}
		});

		metricRegistry.registerAll(new MetricSet() {
			@Override
			public Map<String, Metric> getMetrics() {

				Map<String, Metric> metrics = new HashMap<>();
				metrics.put("geolocationMemoryUsage", new MemoryUsageGaugeSet());
				metrics.put("geolocationClassLoading", new ClassLoadingGaugeSet());
				metrics.put("geolocationGarbageCollector", new GarbageCollectorMetricSet());
				return metrics;
			}
		});
	}

	public Counter geolocationWriteRequestCount() {
		return geolocationWriteRequestCount;
	}

	public void markGeolocationLastWriteTime() {
		geolocationLastWriteTime = System.currentTimeMillis();
	}
}
