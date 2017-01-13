package com.packt.microservices.geolocation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {
  
  @Autowired
  private GeoLocationRepository repository;

  @Override
  public GeoLocation create(GeoLocation geolocation) {
    repository.addGeoLocation(geolocation);
    return geolocation;
  }

  @Override
  public List<GeoLocation> findAll() {
    return repository.getGeoLocations();
  }
}
