package com.photo.app.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppAccountManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppAccountManagmentApplication.class, args);
	}

}
