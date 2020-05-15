package com.angularBootRef.springBootPortfolio.converter;

import com.angularBootRef.springBootPortfolio.domain.User;
import com.angularBootRef.springBootPortfolio.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements IObjectDtoConverter<UserDto, User> {

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDto convertToDto(User src) {
        return null;
    }

    @Override
    public User convertFromDto(UserDto user) {
        final User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordHash(bcryptEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        return null;
    }
}
