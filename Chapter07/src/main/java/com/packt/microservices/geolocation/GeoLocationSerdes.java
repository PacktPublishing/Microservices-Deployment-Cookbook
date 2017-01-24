package com.packt.microservices.geolocation;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;

public class GeoLocationSerdes implements Serde<GeoLocation> {

	private static final Gson GSON = new Gson();

	public GeoLocationSerdes() {}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public void close() {}

	@Override
	public Serializer<GeoLocation> serializer() {
		return new Serializer<GeoLocation>() {
			@Override
			public void configure(Map<String, ?> configs, boolean isKey) {}

			@Override
			public byte[] serialize(String topic, GeoLocation data) {
				return data.toString().getBytes();
			}

			@Override
			public void close() {}
		};
	}

	@Override
	public Deserializer<GeoLocation> deserializer() {
		return new Deserializer<GeoLocation>() {
			@Override
			public void configure(Map<String, ?> configs, boolean isKey) {}

			@Override
			public GeoLocation deserialize(String topic, byte[] data) {
				return GSON.fromJson(new String(data), GeoLocation.class);
			}

			@Override
			public void close() {}
		};
	}
}
