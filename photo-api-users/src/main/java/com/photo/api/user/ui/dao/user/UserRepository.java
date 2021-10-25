package com.photo.api.user.ui.dao.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.photo.api.user.ui.model.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	User findByUserId(String userId);
}
