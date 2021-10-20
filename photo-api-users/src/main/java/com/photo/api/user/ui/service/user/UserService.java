package com.photo.api.user.ui.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.photo.api.user.ui.model.dto.user.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto dto);
	
	UserDto getUserDetailsByEmail(String email);
}
