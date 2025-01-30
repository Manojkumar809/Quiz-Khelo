package com.quiz_backend.quiz_service.model;

import java.util.List;
import lombok.Data;

@Data
public class UserPerformance {
    private int quizzesCount; 
    private double averageScore;
    private List<QuizOutro> quizData; 
}
