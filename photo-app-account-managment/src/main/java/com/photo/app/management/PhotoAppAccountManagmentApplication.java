package com.photo.app.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhotoAppAccountManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppAccountManagmentApplication.class, args);
	}

}
