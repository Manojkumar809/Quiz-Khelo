package com.quiz_backend.quiz_service.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    public int createQuiz(String title, String topic, int count) {
        List<Integer> questions = quizInterface.getQuizQuestions(topic, count);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        int quizId = dao.save(quiz).getId();
        return quizId;
    }
    
    public List<QuestionWrapper> getQuizQuestions(int quizId) {
        List<Integer> questionIds = dao.findById(quizId).get().getQuestionsIds();
        return quizInterface.getQuestionsFromIds(questionIds);
    }

    public int calculateScore(int quizId, List<QuizResponse> responses) {
        return quizInterface.getScore(responses);
    }
    
}
