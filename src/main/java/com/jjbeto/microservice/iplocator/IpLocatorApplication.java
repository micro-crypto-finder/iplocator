package com.jjbeto.microservice.iplocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IpLocatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpLocatorApplication.class, args);
	}

}
