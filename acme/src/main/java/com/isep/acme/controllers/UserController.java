package com.isep.acme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.isep.acme.model.UserView;
import com.isep.acme.services.UserService;

@RestController
@RequestMapping(path = "/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable final Long userId) {

        return userService.getUser(userId);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDetails> create(@PathVariable final String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        return ResponseEntity.ok().body(userDetails);
    }
}
