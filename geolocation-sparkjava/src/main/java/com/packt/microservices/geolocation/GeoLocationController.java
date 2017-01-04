package com.packt.microservices.geolocation;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

public class GeoLocationController {

	public static void main(String[] args) {
		GeoLocationService service = new GeoLocationServiceImpl();
		Gson gson = new Gson();

		get("/geolocation", (req, resp) -> {
			return service.findAll();
		}, gson::toJson);

		post("/geolocation", (req, resp) -> {
			return service.create(gson.fromJson(req.body(), GeoLocation.class));
		}, gson::toJson);
	}

}
