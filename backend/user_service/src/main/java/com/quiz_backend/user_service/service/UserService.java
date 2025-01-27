package com.quiz_backend.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.quiz_backend.user_service.model.Users;
import com.quiz_backend.user_service.repo.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<String> register(Users user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return new ResponseEntity<>("Username cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        try {
            Users extingUser = repo.findByUsername(user.getUsername());
            if (extingUser != null) {
                return new ResponseEntity<>("There is an existing user", HttpStatus.BAD_REQUEST);
            }
            user.setPassword(encoder.encode(user.getPassword()));
            repo.save(user);
            return new ResponseEntity<>("User Successfully Created", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Failed to create User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> verify(Users user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return new ResponseEntity<>("Username cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        try {
            Authentication authentication = authManager.authenticate(new 
                UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());
                return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }  catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Failed to authenticate user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Boolean> authorizeToken(String authHeader) {
        try {
            String token = null;
            String username = null;
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }
            UserDetails userDetails = context.getBean(MyUserDetailsService.class)
                                             .loadUserByUsername(username);
            if(jwtService.validateToken(token, userDetails)){
                return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
    
}
