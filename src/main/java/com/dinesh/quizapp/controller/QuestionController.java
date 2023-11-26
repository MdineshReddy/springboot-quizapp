package com.dinesh.quizapp.controller;

import com.dinesh.quizapp.entity.Question;
import com.dinesh.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{name}")
    public List<Question> getQuestionsByCategory(@PathVariable("name") String name){
        System.out.println(name);
        return  questionService.getQuestionsByCategory(name);
    }

    @PostMapping("add")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
