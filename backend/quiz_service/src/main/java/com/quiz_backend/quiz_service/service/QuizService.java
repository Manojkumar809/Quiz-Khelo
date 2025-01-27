package com.quiz_backend.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quiz_backend.quiz_service.dao.QuizDao;
import com.quiz_backend.quiz_service.feign.QuizInterface;
import com.quiz_backend.quiz_service.model.QuestionWrapper;
import com.quiz_backend.quiz_service.model.Quiz;
import com.quiz_backend.quiz_service.model.QuizResponse;

@Service
public class QuizService {

    @Autowired
    private QuizDao dao;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<Integer> createQuiz(String title, String topic, int count) {
        try {
            List<Integer> questions = quizInterface.getQuizQuestions(topic, count).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionsIds(questions);
            int quizId = dao.save(quiz).getId();
            return new ResponseEntity<>(quizId, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizId) {
        List<QuestionWrapper> quizQuestions = new ArrayList<>();
        try {
            List<Integer> questionIds = dao.findById(quizId).get().getQuestionsIds();
            quizQuestions = quizInterface.getQuestionsFromIds(questionIds).getBody();
            return new ResponseEntity<>(quizQuestions, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(quizQuestions, HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<Integer> calculateScore(int quizId, List<QuizResponse> responses) {
        try {
            int score = quizInterface.getScore(responses).getBody();
            return new ResponseEntity<>(score, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }
    
}
