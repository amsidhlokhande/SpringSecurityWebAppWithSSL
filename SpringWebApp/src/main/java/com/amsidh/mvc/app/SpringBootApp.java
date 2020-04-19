package com.amsidh.mvc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.amsidh.mvc" }, excludeName = "entityManagerFactory")
@EntityScan(basePackages = { "com.amsidh.mvc.entity" })
@EnableJpaRepositories(basePackages = { "com.amsidh.mvc.repository" })
public class SpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);

	}

}
