package com.quiz_backend.quiz_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.quiz_backend.quiz_service.model.QuestionWrapper;
import com.quiz_backend.quiz_service.model.QuizResponse;
import com.quiz_backend.quiz_service.service.QuizService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/quiz")
public class QuizController {
    
    @Autowired
    private QuizService service;
    
    @GetMapping("/create")
    public ResponseEntity<Integer> createQuiz(@RequestParam String title, @RequestParam String topic, @RequestParam int count){
        return service.createQuiz(title, topic, count);
    }
    
    @GetMapping("/getQuiz")
    public ResponseEntity<List<QuestionWrapper>> getMethodName(@RequestParam int quizId) {
        return service.getQuizQuestions(quizId);
    }
    
    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestParam int quizId, @RequestBody List<QuizResponse> responses) {
        return service.calculateScore(quizId, responses);
    }
    
}
