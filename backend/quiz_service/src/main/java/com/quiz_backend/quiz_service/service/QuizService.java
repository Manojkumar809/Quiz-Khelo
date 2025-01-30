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
import com.quiz_backend.quiz_service.model.QuizOutro;
import com.quiz_backend.quiz_service.model.QuizResponse;
import com.quiz_backend.quiz_service.model.UserPerformance;

@Service
public class QuizService {

    @Autowired
    private QuizDao dao;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<Integer> createQuiz(Quiz quiz) {
        try {
            String topic = quiz.getTopic();
            int count = quiz.getQuestionsCount();
            List<Integer> questions = quizInterface.getQuizQuestions(topic, count).getBody();
            Quiz createQuiz = new Quiz();
            createQuiz.setUsername(quiz.getUsername());
            createQuiz.setTitle(quiz.getTitle());
            createQuiz.setTopic(topic);
            createQuiz.setQuestionsCount(count);
            createQuiz.setQuestionsIds(questions);
            int quizId = dao.save(createQuiz).getId();
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
            Integer score= quizInterface.getScore(responses).getBody();
            if(score != null){
                Quiz quiz = dao.findById(quizId).get();
                quiz.setScore(score);
                dao.save(quiz);
                return new ResponseEntity<>(score, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserPerformance> getPerformance(String username) {
        try {
            List<Quiz> userQuizzes = dao.findAllByUsername(username);
            List<QuizOutro> userQuizzesOutro = new ArrayList<>();
            UserPerformance userPerformance = new UserPerformance();
            double percentageSum = 0;
            for (Quiz quiz : userQuizzes) {
                QuizOutro quizOutro = new QuizOutro();
                quizOutro.setTitle(quiz.getTitle());
                quizOutro.setTopic(quiz.getTopic());
                quizOutro.setQuestionsCount(quiz.getQuestionsCount());
                quizOutro.setScore(quiz.getScore());
                double percentage = ( (double) quiz.getScore()/quiz.getQuestionsCount()) * 100;
                percentageSum += percentage;
                userQuizzesOutro.add(quizOutro);
            }
            userPerformance.setQuizzesCount(userQuizzes.size());
            userPerformance.setAverageScore((double)(percentageSum/userQuizzes.size()));
            userPerformance.setQuizData(userQuizzesOutro);
            return new ResponseEntity<>(userPerformance, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new UserPerformance(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
