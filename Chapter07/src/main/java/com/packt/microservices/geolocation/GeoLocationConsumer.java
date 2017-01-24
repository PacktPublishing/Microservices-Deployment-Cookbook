package com.packt.microservices.geolocation;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.google.gson.Gson;

public class GeoLocationConsumer implements Runnable {

	private static final Gson GSON = new Gson();
	private static final GeoLocationRepository REPO = new GeoLocationRepository();

	public void run() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.99.100:9092");
		props.put("group.id", "geolocationConsumer");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());

		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
			consumer.subscribe(Arrays.asList("geolocations"));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf("offset = %d, key = %s, value = %s%n", 
							record.offset(), 
							record.key(), 
							record.value());

					REPO.addGeoLocation(GSON.fromJson(record.value(), GeoLocation.class));
				}
			}
		} catch (Exception e) {
			System.err.println("Error while consuming geolocations. Details: " + e.getMessage());
		}
	}
}
