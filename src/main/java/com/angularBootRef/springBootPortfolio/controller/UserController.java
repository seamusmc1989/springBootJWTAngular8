package com.angularBootRef.springBootPortfolio.controller;

import com.angularBootRef.springBootPortfolio.domain.User;
import com.angularBootRef.springBootPortfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){

        //TODO add in optional here and throw custom exception if not found

        return userService.findById(id).get();
    }

}

