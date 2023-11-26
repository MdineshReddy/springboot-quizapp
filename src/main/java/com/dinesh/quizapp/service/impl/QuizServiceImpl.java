package com.dinesh.quizapp.service.impl;

import com.dinesh.quizapp.dao.QuestionDao;
import com.dinesh.quizapp.dao.QuizDao;
import com.dinesh.quizapp.entity.Question;
import com.dinesh.quizapp.entity.QuestionWrapper;
import com.dinesh.quizapp.entity.Quiz;
import com.dinesh.quizapp.entity.Response;
import com.dinesh.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    @Override
    public ResponseEntity<String> createQuiz(String title, int numQ, String category) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        questionsFromDB.stream().forEach(q->questionsForUser.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4())));

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> submitQuiz(int id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        int count = 0;
        for(Question q: questionsFromDB){
            for(Response response: responses){
                if(response.getId() == q.getId()){
                    if(response.getResponse().equals(q.getAnswer())){
                        count++;
                    }
                    break;
                }
            }
        }
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
