package com.packt.microservices.geolocation;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/geolocation")
public class GeoLocationApplication extends Application {

  public GeoLocationApplication() {}
}
