package com.myservice.store.serviceclient;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceClientApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ServiceClientApplication.class, args);
		SpringApplication app = new SpringApplication(ServiceClientApplication.class);
		app.setDefaultProperties(Collections
			.singletonMap("server.port", "8085"));
		app.run(args);
	}

}
