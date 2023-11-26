package com.dinesh.quizapp.service;

import com.dinesh.quizapp.entity.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<List<Question>> getAllQuestions();

    List<Question> getQuestionsByCategory(String name);

    Question addQuestion(Question question);
}
