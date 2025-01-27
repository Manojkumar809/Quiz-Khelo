package com.quiz_backend.question_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quiz_backend.question_service.dao.QuestionDAO;
import com.quiz_backend.question_service.model.Question;
import com.quiz_backend.question_service.model.QuestionWrapper;
import com.quiz_backend.question_service.model.QuizResponse;

@Service
public class QuestionService {

    @Autowired
    private QuestionDAO dao;

    public ResponseEntity<List<Integer>> getQuizQuestions(String topic, int count) {
        List<Integer> quizQuestionsIds = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, count);
            List<Question> questions = dao.createQuizQuestions(topic, pageable);
            for (Question question : questions) {
                quizQuestionsIds.add(question.getId());
            }
            return new ResponseEntity<>(quizQuestionsIds, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(quizQuestionsIds, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> quizQuestions = new ArrayList<>();
        try {
            List<Question> questions = dao.findAllById(questionIds);
            for (Question question : questions) {
                QuestionWrapper questionWrapper = new QuestionWrapper();
                questionWrapper.setId(question.getId());
                questionWrapper.setQuestionTitle(question.getQuestionTitle());
                questionWrapper.setDifficultyLevel(question.getDifficultyLevel());
                questionWrapper.setOption1(question.getOption1());
                questionWrapper.setOption2(question.getOption2());
                questionWrapper.setOption3(question.getOption3());
                questionWrapper.setOption4(question.getOption4());
                quizQuestions.add(questionWrapper);
            }
            return new ResponseEntity<>(quizQuestions, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(quizQuestions, HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<Integer> getScore(List<QuizResponse> responses) {
        int score = -1;
        try {
            for (QuizResponse quizResponse : responses) {
                int questionId = quizResponse.getId();
                String answer = quizResponse.getAnswer();
                Question question = dao.findById(questionId).get();
                if(question.getRightAnswer().equals(answer)){
                    score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(score, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<String>> getTopics() {
        List<String> topics = new ArrayList<>();
        try {
            topics =  dao.getAllTopics();
            return new ResponseEntity<>(topics, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(topics, HttpStatus.BAD_REQUEST);
        }
    }

}
