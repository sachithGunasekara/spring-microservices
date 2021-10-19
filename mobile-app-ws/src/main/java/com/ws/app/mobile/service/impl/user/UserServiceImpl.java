package com.ws.app.mobile.service.impl.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.app.mobile.service.user.UserService;
import com.ws.app.mobile.service.utility.UtilityService;
import com.ws.app.mobile.ui.model.request.UpdateUserDetailRequest;
import com.ws.app.mobile.ui.model.request.UserDetailRequest;
import com.ws.app.mobile.ui.model.response.UserRest;

@Service
public class UserServiceImpl implements UserService {

	private Map<String, UserRest> users = new HashMap<>();

	private UtilityService utilityService;

	public UserServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public UserServiceImpl(UtilityService utilityService) {
		super();
		this.utilityService = utilityService;
	}

	@Override
	public UserRest createUser(UserDetailRequest userDetailRequest) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetailRequest.getEmail());
		returnValue.setFirstName(userDetailRequest.getFirstName());
		returnValue.setLastName(userDetailRequest.getLastName());
		returnValue.setPassword(userDetailRequest.getPassword());

		String userId = this.utilityService.generateUserId();
		returnValue.setUserId(userId);
		users.put(userId, returnValue);
		return returnValue;
	}

	@Override
	public UserRest getUser(String userId) {
		return users.get(userId);
	}

	@Override
	public UserRest updateUser(String userId, UpdateUserDetailRequest updateUserDetailRequest) {
		UserRest storedUser = users.get(userId);
		storedUser.setFirstName(updateUserDetailRequest.getFirstName());
		storedUser.setLastName(updateUserDetailRequest.getLastName());
		return storedUser;
	}

	@Override
	public void deleteUser(String userId) {
		users.remove(userId);
	}
	
	

}
