package com.quiz_backend.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.quiz_backend.quiz_service.model.QuestionWrapper;
import com.quiz_backend.quiz_service.model.QuizResponse;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/getQuizQuestions")
    public List<Integer> getQuizQuestions(@RequestParam String topic, @RequestParam int count);

    @PostMapping("question/getQuestions")
    public List<QuestionWrapper> getQuestionsFromIds(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public int getScore(@RequestBody List<QuizResponse> responses);

}
