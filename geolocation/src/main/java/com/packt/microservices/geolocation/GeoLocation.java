package com.packt.microservices.geolocation;

import java.io.Serializable;
import java.util.UUID;

import com.google.gson.Gson;

public class GeoLocation implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new Gson();

	private double latitude;
	private double longitude;
	private UUID userId;
	private long timestamp;

	public GeoLocation() {}

	public GeoLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.userId = UUID.randomUUID();
		this.timestamp = System.currentTimeMillis();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return GSON.toJson(this);
	}

}
