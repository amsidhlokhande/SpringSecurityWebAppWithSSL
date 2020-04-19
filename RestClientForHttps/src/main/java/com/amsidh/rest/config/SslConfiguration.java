package com.amsidh.rest.config;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SslConfiguration {

	@Value("${trust.store}")
	private Resource keyStore;
	@Value("${trust.store.password}")
	private String keyStorePassword;

	@Bean
	RestTemplate restTemplate() throws Exception {
		SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(keyStore.getURL(), keyStorePassword.toCharArray()).build();
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
				NoopHostnameVerifier.INSTANCE);
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		factory.setBufferRequestBody(false);

		return new RestTemplate(factory);
		// return new RestTemplate();
	}
	
	
	@Bean
	AsyncRestTemplate AsyncRestTemplate() throws Exception {
		final SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(keyStore.getURL(), keyStorePassword.toCharArray()).build();
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
				NoopHostnameVerifier.INSTANCE);
		
		
		
		final Registry<SchemeIOSessionStrategy> registryBuilder = RegistryBuilder.<SchemeIOSessionStrategy>create()
        .register("http", NoopIOSessionStrategy.INSTANCE)
        .register("https", new SSLIOSessionStrategy(
        		sslContext, null, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)).build();
		
		final PoolingNHttpClientConnectionManager connectionManager =
                new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT), registryBuilder);
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(25);

        final RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(300)
        .setSocketTimeout(200)
        .setConnectionRequestTimeout(100)
        .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
        .build();
        
        final CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        
		
		AsyncClientHttpRequestFactory factory = new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
		
		return new AsyncRestTemplate(factory);
	}

	
}
