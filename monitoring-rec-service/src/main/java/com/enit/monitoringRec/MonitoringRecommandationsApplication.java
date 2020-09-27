package com.enit.monitoringRec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitoringRecommandationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringRecommandationsApplication.class, args);
	}

}
