package com.packt.microservices.geolocation;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;

public class Zookeeper {

	private String host;
	private int port;

	public Zookeeper(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void register() {
		CuratorFramework curator = CuratorFrameworkFactory.newClient(host + ":" + port, new RetryNTimes(3, 3000));
		curator.start();

		try {
			final ServiceInstance<Object> serviceInstance = ServiceInstance.builder()
					.uriSpec(new UriSpec("{scheme}://{address}:{port}"))
					.address(getIp()).port(getPort())
					.name("geolocation")
					.build();

			final ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class)
					.basePath("com.packt.microservices")
					.client(curator)
					.thisInstance(serviceInstance)
					.build();

			serviceDiscovery.start();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				try {
					serviceDiscovery.unregisterService(serviceInstance);
				} catch (Exception e) {
					System.err.println("Error while unregistering service in Zookeeper. Details: " + e.getMessage());
					e.printStackTrace();
				}
			}));
		} catch (Exception e) {
			System.err.println("Error while registering service in Zookeeper. Details: " + e.getMessage());
			e.printStackTrace();
		}
	}


	private String getIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println("Error while finding local IP. Using localhost for now. Details: " + e.getMessage());
			e.printStackTrace();
			return "localhost";
		}
	}

	private int getPort() {
		try {
			return Integer.valueOf(System.getenv("GEOLOCATION_SERVICE_PORT"));
		} catch(Exception e) {
			System.err.println("Error while finding service port. Using default port 8080. Details: " + e.getMessage());
			e.printStackTrace();
			return 8080;
		}
	}

}
