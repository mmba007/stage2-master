package com.enit.adscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AdsCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdsCrudApplication.class, args);
	}

}
