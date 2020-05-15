package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.converter.UserDtoConverter;
import com.angularBootRef.springBootPortfolio.domain.User;
import com.angularBootRef.springBootPortfolio.dto.UserDto;
import com.angularBootRef.springBootPortfolio.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private UserDtoConverter userDtoConverter;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		log.info(">>>>>>>>>>username query by: "  + username);
		final User user = this.findOne(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		//userDao.delete(id);
	}

	@Override
	public User findOne(String username) {
		final User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}

	@Override
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
	}

	@Override
    public User save(UserDto user) {
		final User newUser = this.userDtoConverter.convertFromDto(user);
	    return userDao.save(newUser);
    }
}
