package com.quiz_backend.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quiz_backend.user_service.model.Users;
import com.quiz_backend.user_service.model.UsersPrincipal;
import com.quiz_backend.user_service.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if(user == null){
            System.out.println("No user found");
            throw new UsernameNotFoundException(username);
        }
        return new UsersPrincipal(user);
    }
    
}
