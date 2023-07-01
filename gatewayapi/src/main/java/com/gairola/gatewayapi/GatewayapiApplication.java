package com.gairola.gatewayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayapiApplication.class, args);
	}

}
