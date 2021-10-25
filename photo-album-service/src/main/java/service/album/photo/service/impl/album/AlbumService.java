package service.album.photo.service.impl.album;

import java.util.List;

import service.album.photo.ui.model.album.AlbumResponseModel;

public interface AlbumService {

	List<AlbumResponseModel> getAlbums();

}