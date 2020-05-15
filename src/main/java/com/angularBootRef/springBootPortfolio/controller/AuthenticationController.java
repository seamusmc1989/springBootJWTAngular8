package com.angularBootRef.springBootPortfolio.controller;


import com.angularBootRef.springBootPortfolio.domain.AuthToken;
import com.angularBootRef.springBootPortfolio.domain.User;
import com.angularBootRef.springBootPortfolio.domain.UserRoleEnum;
import com.angularBootRef.springBootPortfolio.service.UserService;
import com.angularBootRef.springBootPortfolio.configuration.JwtTokenUtil;
import com.angularBootRef.springBootPortfolio.domain.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);

        List<UserRoleEnum> roles =  user.getRoles()
                    .stream()
                    .map(userRole -> userRole.getRole())
                    .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthToken(user.getId(), token, roles));
    }

}

