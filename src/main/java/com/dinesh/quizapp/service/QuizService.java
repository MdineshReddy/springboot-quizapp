package com.dinesh.quizapp.service;

import com.dinesh.quizapp.entity.QuestionWrapper;
import com.dinesh.quizapp.entity.Quiz;
import com.dinesh.quizapp.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    ResponseEntity<String> createQuiz(String title, int numQ, String category);


    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id);

    ResponseEntity<Integer> submitQuiz(int id, List<Response> responses);
}
