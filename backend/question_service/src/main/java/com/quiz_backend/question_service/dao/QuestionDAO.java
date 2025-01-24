package com.quiz_backend.question_service.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.quiz_backend.question_service.model.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{

    @Query(value = "SELECT * FROM question WHERE category=?1 ORDER BY RAND()", nativeQuery=true)
    List<Question> createQuizQuestions(String topic, Pageable pageable);

    @Query(value = "SELECT DISTINCT(category) FROM question", nativeQuery = true)
    List<String> getAllTopics();

}
