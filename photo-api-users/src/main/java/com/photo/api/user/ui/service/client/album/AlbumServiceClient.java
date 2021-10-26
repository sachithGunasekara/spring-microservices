package com.photo.api.user.ui.service.client.album;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.photo.api.user.ui.model.dto.album.AlbumResponseModel;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "album-ws", fallbackFactory = AlbumFallbackFactory.class)
public interface AlbumServiceClient {

	@GetMapping(path = "/users/{id}/albums")
	public List<AlbumResponseModel> getAlbums(@PathVariable(name = "id", required = true) String userId);
}

@Component
class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClient> {

	@Override
	public AlbumServiceClient create(Throwable cause) {
		return new AlbumServiceClientFallback(cause);
	}
}

class AlbumServiceClientFallback implements AlbumServiceClient {

	private Throwable cause;

	
	public AlbumServiceClientFallback() {
	}

	public AlbumServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public List<AlbumResponseModel> getAlbums(String userId) {
		System.out.println(cause.getLocalizedMessage());
		return new ArrayList<>();
	}

}