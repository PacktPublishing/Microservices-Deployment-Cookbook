package com.packt.microservices.geolocation;

import java.util.List;

public interface GeoLocationService {

  public GeoLocation create(GeoLocation geolocation);
  public List<GeoLocation> findAll();
}
