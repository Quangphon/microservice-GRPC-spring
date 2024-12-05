//package com.quangoi.quiz_service.feign;
//
//import com.quangoi.quiz_service.model.QuestionWrapper;
//import com.quangoi.quiz_service.model.Response;
////import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@FeignClient("QUESTION-SERVICE")
////public interface QuizInterface {
////    // Quiz service
////    @GetMapping("/question/generate")
////    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);
////
////    @PostMapping("/question/getQuestions")
////    public ResponseEntity<List<QuestionWrapper>> getQuestsFromId(@RequestBody List<Integer> questionIds);
////
////    // Quiz Service
////    @PostMapping("/question/getScore")
////    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
////}