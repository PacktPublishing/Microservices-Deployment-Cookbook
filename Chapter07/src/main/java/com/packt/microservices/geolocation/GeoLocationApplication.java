package com.packt.microservices.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableDiscoveryClient
public class GeoLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoLocationApplication.class, args);

		// commented out so that the service does not try to connect to zk
		// new Zookeeper("192.168.99.100", 2181).register();

		new Thread(new GeoLocationConsumer()).start();
	}
}
