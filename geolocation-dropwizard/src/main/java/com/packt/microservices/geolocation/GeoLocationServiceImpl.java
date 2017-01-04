package com.packt.microservices.geolocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoLocationServiceImpl implements GeoLocationService {
  
  private static List<GeoLocation> geolocations = new ArrayList<>();

  @Override
  public GeoLocation create(GeoLocation geolocation) {
    geolocations.add(geolocation);
    return geolocation;
  }

  @Override
  public List<GeoLocation> findAll() {
    return Collections.unmodifiableList(geolocations);
  }
}
