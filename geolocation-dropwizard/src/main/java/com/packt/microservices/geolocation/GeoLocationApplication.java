package com.packt.microservices.geolocation;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class GeoLocationApplication extends Application<GeoLocationConfiguration> {
  
  public static void main(String[] args) throws Exception {
    new GeoLocationApplication().run(args);
  }

  @Override
  public void run(GeoLocationConfiguration config, Environment env) throws Exception {
    env.jersey().register(new GeoLocationResource());
  }
}
