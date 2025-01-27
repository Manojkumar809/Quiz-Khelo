package com.quiz_backend.question_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.quiz_backend.question_service.model.QuestionWrapper;
import com.quiz_backend.question_service.model.QuizResponse;
import com.quiz_backend.question_service.service.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("question")
public class QuestionController {
    
    @Autowired
    private QuestionService service;
    
    @GetMapping("/getQuizQuestions")
    public ResponseEntity<List<Integer>> getQuizQuestions(@RequestParam String topic, @RequestParam int count){
        return service.getQuizQuestions(topic, count);
    }
    
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds){
        return service.getQuestionsFromIds(questionIds);
    }

    @GetMapping("/getTopics")
    public ResponseEntity<List<String>> getTopics(){
        return service.getTopics();
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses){
        return service.getScore(responses);
    }
}
