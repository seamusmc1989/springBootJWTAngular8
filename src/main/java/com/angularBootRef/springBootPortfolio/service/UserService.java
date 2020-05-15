package com.angularBootRef.springBootPortfolio.service;

import java.util.List;
import java.util.Optional;

import com.angularBootRef.springBootPortfolio.domain.User;
import com.angularBootRef.springBootPortfolio.dto.UserDto;

public interface UserService {

	User save(UserDto user);
	List<User> findAll();
	void delete(long id);
	User findOne(String username);
	Optional<User> findById(Long id);

}
