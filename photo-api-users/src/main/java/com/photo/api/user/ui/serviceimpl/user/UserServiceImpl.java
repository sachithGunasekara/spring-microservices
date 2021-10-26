package com.photo.api.user.ui.serviceimpl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.photo.api.user.ui.dao.user.UserRepository;
import com.photo.api.user.ui.model.dto.album.AlbumResponseModel;
import com.photo.api.user.ui.model.dto.user.UserDto;
import com.photo.api.user.ui.model.user.User;
import com.photo.api.user.ui.service.client.album.AlbumServiceClient;
import com.photo.api.user.ui.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RestTemplate restTemplate;
	private AlbumServiceClient albumServiceClient;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public UserServiceImpl() {

	}

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RestTemplate restTemplate,AlbumServiceClient albumServiceClient) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.restTemplate = restTemplate;
		this.albumServiceClient = albumServiceClient;
	}

	@Override
	public UserDto createUser(UserDto dto) {
		ModelMapper mp = new ModelMapper();
		mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		dto.setUserId(UUID.randomUUID().toString());
		dto.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

		User user = mp.map(dto, User.class);
		user = this.userRepository.save(user);
		return mp.map(user, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(),
				true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException(email);

		ModelMapper mapper = new ModelMapper();
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		/*String url = String.format("http://ALBUM-WS/users/%s/albums", userId);

		ResponseEntity<List<AlbumResponseModel>> exchange = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AlbumResponseModel>>() {
				});
		List<AlbumResponseModel> albums = exchange.getBody();*/
		logger.info("Before sending request to album service");
		
		List<AlbumResponseModel> albums = albumServiceClient.getAlbums(userId);
		
		logger.info("After sending request to album service");

		ModelMapper mp = new ModelMapper();
		UserDto userDto = mp.map(user, UserDto.class);
		userDto.setAlbums(albums);
		return userDto;
	}

}
