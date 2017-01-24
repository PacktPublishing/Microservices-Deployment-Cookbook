package com.packt.microservices.geolocation;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class GeoLocationProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.99.100:9092");
		props.put("key.serializer", StringSerializer.class.getName());
		props.put("value.serializer", StringSerializer.class.getName());
		Producer<String, String> producer = new KafkaProducer<>(props);

		List<GeoLocation> geolocations = Arrays.asList(
				new GeoLocation(38.6270, 90.1994),
				new GeoLocation(93.9879, 76.9876), // invalid lat
				new GeoLocation(41.8034, -88.1440),
				new GeoLocation(40.9879, -200.9876), // invalid long
				new GeoLocation(-93.9879, 76.9876), // invalid lat
				new GeoLocation(9.5680, 77.9624),
				new GeoLocation(13.0827, 80.2707),
				new GeoLocation(40.9879, 200.9876), // invalid long
				new GeoLocation(9.9252, 78.1198));

		for(GeoLocation geolocation : geolocations) {
			System.out.println("Sending geolocaiton [" + geolocation.toString() + "]");
			ProducerRecord<String, String> record = new ProducerRecord<>(
					"geolocationJob", 
					geolocation.toString());
			producer.send(record);
		}

		producer.close();
	}
}
