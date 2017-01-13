package com.packt.microservices.geolocation.lb;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/geolocation")
public class GeolocationProxyController {

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String create(@RequestBody String body) throws Exception {
		URI serviceUri = ZookeeperServiceDiscovery.getGeolocationServiceUri();

		System.out.println("Proxying POST request to service " + serviceUri.toString() + " at path " + request.getRequestURI());
		URI uri = new URI(serviceUri.getScheme(), 
				null, 
				serviceUri.getHost(), 
				serviceUri.getPort(), 
				request.getRequestURI(), 
				request.getQueryString(), 
				null);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

		return restTemplate.exchange(uri, HttpMethod.POST, entity, String.class).getBody();
	}

	@RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findAll() throws Exception {
		URI serviceUri = ZookeeperServiceDiscovery.getGeolocationServiceUri();

		System.out.println("Proxying GET request to service " + serviceUri.toString() + " at path " + request.getRequestURI());
		URI uri = new URI(serviceUri.getScheme(), 
				null, 
				serviceUri.getHost(), 
				serviceUri.getPort(), 
				request.getRequestURI(), 
				request.getQueryString(), 
				null);

		return restTemplate.getForEntity(uri, String.class).getBody();
	}
}
