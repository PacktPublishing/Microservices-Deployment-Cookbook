package com.packt.microservices.geolocation.lb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeolocationZkLoadBalancer {

	public static void main(String[] args) {
		SpringApplication.run(GeolocationZkLoadBalancer.class, args);
	}
}
