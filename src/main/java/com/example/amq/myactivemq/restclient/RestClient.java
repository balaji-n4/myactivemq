package com.example.amq.myactivemq.restclient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.amq.myactivemq.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

	@Value("${shoppingcart.products.endpoint.url}")
	private String url;

	public void create(Product product) {
		try {
			String inputJson = mapToJson(product);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(inputJson, headers);
			restTemplate.postForObject(url, request, String.class);
		} catch (Exception e) {
			System.out.println("JSON processing exception");
		}
	}

	public void update(Product product) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("prodId", product.getProdId());
//		url = "http://localhost:9090/products/{prodId}";
		product.setProdName("NewLaptop");
		restTemplate.put(url + "/{prodId}", product, params);
	}

	public void delete(String prodId) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("prodId", prodId);
//		url = "http://localhost:9090/products/{prodId}";
		restTemplate.delete(url + "/{prodId}" , params);
	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

}
