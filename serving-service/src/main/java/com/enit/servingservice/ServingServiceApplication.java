package com.enit.servingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServingServiceApplication.class, args);
	}

}
