package com.quangoi.quiz_service.controller;


import com.quangoi.quiz_service.QuestionServiceGrpc;
//import com.quangoi.quiz_service.config.GrpcClient;
import com.quangoi.quiz_service.model.*;
import com.quangoi.quiz_service.service.QuizService;
import com.quangoi.quiz_service.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto);
    }

    @PostMapping("/get")
    public ResponseEntity<List<QuestionWrappers>> getQuizQuestions(@RequestBody List<Integer> ids) {
        return quizService.getQuizQuestions(ids);
    }

    @PostMapping("/submit")
    public ResponseEntity<Score> submitQuiz(@RequestBody List<Answer> Answers) {
        return  quizService.calculateResult(Answers);
    }

}
