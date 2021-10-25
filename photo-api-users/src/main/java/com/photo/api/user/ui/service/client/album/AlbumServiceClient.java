package com.photo.api.user.ui.service.client.album;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.photo.api.user.ui.model.dto.album.AlbumResponseModel;

@FeignClient("album-ws")
public interface AlbumServiceClient {

	@GetMapping(path = "/users/{id}/albumss")
	public List<AlbumResponseModel> getAlbums(@PathVariable(name = "id",required = true) String userId);
}
