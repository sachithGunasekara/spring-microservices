package com.ws.app.mobile.service.user;

import com.ws.app.mobile.ui.model.request.UpdateUserDetailRequest;
import com.ws.app.mobile.ui.model.request.UserDetailRequest;
import com.ws.app.mobile.ui.model.response.UserRest;

public interface UserService {

	UserRest createUser(UserDetailRequest userDetailRequest);
	
	UserRest getUser(String userId);
	
	UserRest updateUser(String userId, UpdateUserDetailRequest updateUserDetailRequest);
	
	void deleteUser(String userId);
}
