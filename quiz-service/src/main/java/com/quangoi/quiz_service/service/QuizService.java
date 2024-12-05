package com.quangoi.quiz_service.service;

import com.quangoi.quiz_service.dao.QuizDao;
import com.quangoi.quiz_service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizServiceImpl quizServiceImpl;

    public ResponseEntity<String> createQuiz(QuizDto quizDto) {
        List<Integer> questions = quizServiceImpl.getQuestionsForQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions());
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrappers>> getQuizQuestions(List<Integer> ids) {
        List<QuestionWrappers> response = quizServiceImpl.getQuizQuestions(ids);
//        Quiz quiz = quizDao.findById(id).get();
//        List<Integer> questionIds = quiz.getQuestionIds();
//
//        ResponseEntity<List<QuestionWrappers>> questions= quizInterface.getQuestsFromId(questionIds);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//
    public ResponseEntity<Score> calculateResult(List<Answer> Answers) {

        Integer num = quizServiceImpl.calculateResult(Answers);
        Score score = new Score();
        score.setScore(num);

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
