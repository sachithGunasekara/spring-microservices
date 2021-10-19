package com.ws.app.mobile.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws.app.mobile.service.user.UserService;
import com.ws.app.mobile.ui.model.request.UpdateUserDetailRequest;
import com.ws.app.mobile.ui.model.request.UserDetailRequest;
import com.ws.app.mobile.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc") String sort) {
		return "this is get users";
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable(value = "userId", required = true) String userId) {
		UserRest existingUser = this.userService.getUser(userId);
		if (existingUser!=null) {
			return new ResponseEntity<>(existingUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailRequest userDetailRequest) {
		UserRest createdUser = userService.createUser(userDetailRequest);
		return new ResponseEntity<UserRest>(createdUser, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> updateUser(@PathVariable(value = "userId", required = true) String userId,
			@Valid @RequestBody UpdateUserDetailRequest updateUserDetailRequest) {
		UserRest storedUser = this.userService.updateUser(userId, updateUserDetailRequest);
		return new ResponseEntity<UserRest>(storedUser, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<UserRest> deleteUser(@PathVariable(value = "userId", required = true) String userId) {
		this.userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
