package com.quiz_backend.user_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz_backend.user_service.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

    public Users findByUsername(String username);
    
}
