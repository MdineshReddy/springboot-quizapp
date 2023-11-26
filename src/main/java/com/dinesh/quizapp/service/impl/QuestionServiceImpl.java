package com.dinesh.quizapp.service.impl;

import com.dinesh.quizapp.dao.QuestionDao;
import com.dinesh.quizapp.entity.Question;
import com.dinesh.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
           return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Question> getQuestionsByCategory(String name) {
        return questionDao.findByCategoryIgnoreCase(name);
    }

    @Override
    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }
}
