package com.quiz_backend.quiz_service.model;

import lombok.Data;

@Data
public class QuizOutro {
    private String title;
    private String topic;
    private int questionsCount;
    private int score;
}
