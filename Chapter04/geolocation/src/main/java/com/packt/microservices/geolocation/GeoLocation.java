package com.packt.microservices.geolocation;

import java.io.Serializable;
import java.util.UUID;

public class GeoLocation implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private double latitude;
  private double longitude;
  private UUID userId;
  private long timestamp;
  
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
}
