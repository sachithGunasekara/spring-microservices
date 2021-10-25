package com.photo.api.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photo.api.user.ui.model.dto.user.UserDto;
import com.photo.api.user.ui.model.dto.user.UserResponseModel;
import com.photo.api.user.ui.model.request.CreateUserDetailRequest;
import com.photo.api.user.ui.model.response.user.CreateUserResponse;
import com.photo.api.user.ui.service.user.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@GetMapping(path = "/status/check")
	public String getStatus() {
		return "working on " + env.getProperty("local.server.port");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserDetailRequest request) {
		ModelMapper mp = new ModelMapper();
		mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto savedUser = userService.createUser(mp.map(request, UserDto.class));
		CreateUserResponse response = mp.map(savedUser, CreateUserResponse.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(value = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponseModel> getUser(@PathVariable(name = "userId", required = true) String userId) {
		UserDto userDto = userService.getUserByUserId(userId);
		ModelMapper mp = new ModelMapper();
		UserResponseModel userResponse = mp.map(userDto, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}
}
