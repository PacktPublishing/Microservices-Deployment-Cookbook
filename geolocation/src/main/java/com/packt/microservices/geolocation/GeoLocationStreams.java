package com.packt.microservices.geolocation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.stereotype.Component;

@Component
public class GeoLocationStreams {

	@PostConstruct
	public void init() {
		Map<String, Object> props = new HashMap<>();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "geolocation-application");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:9092");
		props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, GeoLocationSerdes.class.getName());

		StreamsConfig config = new StreamsConfig(props);
		KStreamBuilder builder = new KStreamBuilder();

		builder.stream("geolocationStreams").filter(new Predicate<Object, Object>() {
			@Override
			public boolean test(Object key, Object value) {
				GeoLocation geolocation = (GeoLocation) value;
				System.out.println("Stream received => " + value);
				return geolocation.getLatitude() >= -90 
						&& geolocation.getLatitude() < 90 
						&& geolocation.getLongitude() >= -180 
						&& geolocation.getLongitude() < 180;
			}
		}).to("geolocations");

		KafkaStreams streams = new KafkaStreams(builder, config);
		streams.start();
	}
}
