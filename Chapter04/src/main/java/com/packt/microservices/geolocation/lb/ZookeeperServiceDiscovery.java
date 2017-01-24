package com.packt.microservices.geolocation.lb;

import java.net.URI;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceProvider;

public class ZookeeperServiceDiscovery {

	private static ServiceProvider<Object> geolocationServiceProvider;

	private static ServiceProvider<Object> getGeolocationServiceProvider() throws Exception {
		if(geolocationServiceProvider == null) {
			CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("192.168.99.100:2181", new RetryNTimes(5, 1000));
			curatorFramework.start();

			ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class)
					.basePath("com.packt.microservices")
					.client(curatorFramework)
					.build();
			serviceDiscovery.start();

			geolocationServiceProvider = serviceDiscovery.serviceProviderBuilder()
					.serviceName("geolocation")
					.build();
			geolocationServiceProvider.start();
		}
		return geolocationServiceProvider;
	}

	public static URI getGeolocationServiceUri() throws Exception {
		return new URI(getGeolocationServiceProvider().getInstance().buildUriSpec());
	}
}
