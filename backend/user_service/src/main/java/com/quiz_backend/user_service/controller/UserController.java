package com.quiz_backend.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quiz_backend.user_service.model.Users;
import com.quiz_backend.user_service.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user){
        return service.verify(user);
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user){
        return service.register(user);
    }

    @GetMapping("/see")
    public String test() {
        return "hello welcome to see page";
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> authorizeToken(@RequestHeader("Authorization") String authHeader) {
        return service.authorizeToken(authHeader);
    }
    
}
