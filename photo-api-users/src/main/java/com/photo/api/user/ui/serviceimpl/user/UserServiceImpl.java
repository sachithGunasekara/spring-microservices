package com.photo.api.user.ui.serviceimpl.user;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.photo.api.user.ui.dao.user.UserRepository;
import com.photo.api.user.ui.model.dto.user.UserDto;
import com.photo.api.user.ui.model.user.User;
import com.photo.api.user.ui.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl() {

	}

	@Autowired
	public UserServiceImpl(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
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

}
