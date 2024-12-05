package com.quangoi.question_service.service;

import com.quangoi.question_service.dao.QuestionDao;
import com.quangoi.question_service.model.Question;
import com.quangoi.question_service.model.QuestionWrapper;
import com.quangoi.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getALlQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }


    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    // Quiz
//    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
//        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
//        return new ResponseEntity<>(questions, HttpStatus.OK);
//    }
//
//    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
//        List<QuestionWrapper> wrappers = new ArrayList<>();
//        List<Question> questions = new ArrayList<>();
//
//        for (Integer questionId : questionIds) {
//            questions.add(questionDao.findById(questionId).get());
//        }
//        for (Question question : questions) {
//            QuestionWrapper wrapper = new QuestionWrapper();
//            wrapper.setId(question.getId());
//            wrapper.setQuestionTitle(question.getQuestionTitle());
//            wrapper.setOption1(question.getOption1());
//            wrapper.setOption2(question.getOption2());
//            wrapper.setOption3(question.getOption3());
//            wrapper.setOption4(question.getOption4());
//            wrappers.add(wrapper);
//        }
//        return new ResponseEntity<>(wrappers, HttpStatus.OK);
//    }
//
//    public ResponseEntity<Integer> getScore(List<Response> responses) {
//        int right =0;
//        for (Response r : responses) {
//            Question question = questionDao.findById(r.getId()).get();
//            if (r.getResponse().equals(question.getRightAnswer()))
//                right++;
//        }
//        return new ResponseEntity<>(right, HttpStatus.OK);
//    }
}
