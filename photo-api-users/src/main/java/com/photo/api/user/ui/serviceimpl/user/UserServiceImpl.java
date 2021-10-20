package com.photo.api.user.ui.serviceimpl.user;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) throw new UsernameNotFoundException(username);
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null) throw new UsernameNotFoundException(email);
		
		ModelMapper mapper = new ModelMapper();
		return mapper.map(user,UserDto.class);
	}

	
	
}
