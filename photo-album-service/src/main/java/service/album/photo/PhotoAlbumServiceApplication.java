package service.album.photo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhotoAlbumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAlbumServiceApplication.class, args);
	}

}
