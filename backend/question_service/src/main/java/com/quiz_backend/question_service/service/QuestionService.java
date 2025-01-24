package com.quiz_backend.question_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.quiz_backend.question_service.dao.QuestionDAO;
import com.quiz_backend.question_service.model.Question;
import com.quiz_backend.question_service.model.QuestionWrapper;
import com.quiz_backend.question_service.model.QuizResponse;

@Service
public class QuestionService {

    @Autowired
    private QuestionDAO dao;
    
    public List<Question> getAllQuestions(){
        return dao.findAll();
    }

    public List<Integer> getQuizQuestions(String topic, int count) {
        Pageable pageable = PageRequest.of(0, count);
        List<Question> questions = dao.createQuizQuestions(topic, pageable);
        List<Integer> quizQuestionsIds = new ArrayList<>();
        for (Question question : questions) {
            quizQuestionsIds.add(question.getId());
        }
        return quizQuestionsIds;
    }

    public List<QuestionWrapper> getQuestionsFromIds(List<Integer> questionIds) {
        List<Question> questions = dao.findAllById(questionIds);
        List<QuestionWrapper> quizQuestions = new ArrayList<>();
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
        return quizQuestions;
    }
    
    public int getScore(List<QuizResponse> responses) {
        int score = 0;
        for (QuizResponse quizResponse : responses) {
            int questionId = quizResponse.getId();
            String answer = quizResponse.getAnswer();
            Question question = dao.findById(questionId).get();
            if(question.getRightAnswer().equals(answer)){
                score++;
            }
        }
        return score;
    }

    public List<String> getTopics() {
        return dao.getAllTopics();
    }

}
