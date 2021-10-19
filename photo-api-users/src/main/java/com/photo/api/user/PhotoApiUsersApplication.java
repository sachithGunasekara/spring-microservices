package com.photo.api.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhotoApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoApiUsersApplication.class, args);
	}

}
