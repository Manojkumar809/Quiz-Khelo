package com.quiz_backend.quiz_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz_backend.quiz_service.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

    List<Quiz> findAllByUsername(String username);
    
}
