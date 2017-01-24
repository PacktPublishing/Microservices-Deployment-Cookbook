package com.packt.microservices.geolocation.lb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/geolocation")
public class GeolocationProxyController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findAll() throws Exception {
		return restTemplate.getForObject("http://geolocation/geolocation", String.class);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String create(@RequestBody String body) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

		return restTemplate.exchange("http://geolocation/geolocation", HttpMethod.POST, entity, String.class).getBody();
	}
}
