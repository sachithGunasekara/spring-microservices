package service.album.photo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.album.photo.service.impl.album.AlbumService;
import service.album.photo.ui.model.album.AlbumResponseModel;

@RestController
@RequestMapping(path = "/users/{id}/albums")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<AlbumResponseModel> checkStatus() {
		return albumService.getAlbums();
	}
}
