
package com.amsidh.rest;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class RestTemplateApp implements CommandLineRunner {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	public static void main(String[] args) throws Exception {
		SpringApplication springApplication = new SpringApplicationBuilder().sources(RestTemplateApp.class)
				.web(WebApplicationType.NONE).build();

		springApplication.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		ResponseEntity<String> response = restTemplate.getForEntity("https://localhost:8443/welcome", String.class,
				Collections.emptyMap());
		assertEquals("SpringRestApi is up and running fine", response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		ResponseEntity<String> response2 = restTemplate.getForEntity("https://localhost:8443/person", String.class,
				Collections.emptyMap());
		System.out.println("SpringRestApi is up and running fine" + response2.getBody());
		assertEquals(HttpStatus.OK, response2.getStatusCode());

		System.out.println("\n\nCalling Rest API Using AsyncRestTemplate");

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

		ListenableFuture<ResponseEntity<String>> asyncResponse = asyncRestTemplate
				.exchange(new URI("https://localhost:8443/person"), HttpMethod.GET, httpEntity, String.class);

		asyncResponse.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

			@Override
			public void onSuccess(ResponseEntity<String> responseEntity) {
				System.out.println("Http Status=" + responseEntity.getStatusCode());
				System.out.println(responseEntity.getBody());
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Got Exception in Rest Call");
				ex.printStackTrace();

			}

		});
	}

}
