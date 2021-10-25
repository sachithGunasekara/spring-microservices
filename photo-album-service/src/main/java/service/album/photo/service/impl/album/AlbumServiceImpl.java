package service.album.photo.service.impl.album;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import service.album.photo.ui.model.album.AlbumResponseModel;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Override
	public List<AlbumResponseModel> getAlbums(){
		List<AlbumResponseModel> list = new ArrayList<>();
		AlbumResponseModel a1=new AlbumResponseModel();
		a1.setAlbumId("album1");
		a1.setDescription("album1 description");
		a1.setName("album 1");
		a1.setUserId("user1");
		
		AlbumResponseModel a2=new AlbumResponseModel();
		a2.setAlbumId("album2");
		a2.setDescription("album2 description");
		a2.setName("album 2");
		a2.setUserId("user2");
		
		list.add(a1);
		list.add(a2);
		
		return list;
	}
}
