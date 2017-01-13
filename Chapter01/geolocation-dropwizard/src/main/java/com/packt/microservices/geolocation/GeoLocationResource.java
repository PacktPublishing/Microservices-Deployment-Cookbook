package com.packt.microservices.geolocation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/geolocation")
public class GeoLocationResource {

	private GeoLocationService service = new GeoLocationServiceImpl();

	@GET
	@Produces("application/json")
	public List<GeoLocation> findAll() {
	  return service.findAll();
	}
	  
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public GeoLocation create(GeoLocation geolocation) {
	  return service.create(geolocation);
	}

}
