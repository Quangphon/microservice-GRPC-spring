package com.quangoi.question_service.controller;

import com.quangoi.question_service.model.Question;
import com.quangoi.question_service.service.QuestionService;
import com.quangoi.question_service.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionServiceImpl questionServiceImpl;

    @Autowired
    Environment environment;

    //Lấy tất cả questions
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getALlQuestions();
    }

    //Lấy question theo category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    //Add question
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // Tạo mới một quiz gồm categoryName và num questions (QUIZ)
//    @GetMapping("/generate")
//    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions) {
//        return questionServiceImpl.getQuestionsForQuiz(categoryName, numQuestions);
//    }
//
//    // Quiz Service
//    @PostMapping("/getQuestions")
//    public ResponseEntity<List<QuestionWrapper>> getQuestsFromId(@RequestBody List<Integer> questionIds) {
//        System.out.println(environment.getProperty("local.server.port"));
//        return questionServiceImpl.getQuestionsFromId( questionIds);
//    }
//
//    // Lấy điểm gồm id các question và câu trả lời
//    @PostMapping("/getScore")
//    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response) {
//        return questionServiceImpl.getScore(response);
//    }
}
//    @Autowired
//    QuestionServiceGrpc.QuestionServiceBlockingStub stub;


//        @GetMapping("/allQuestions")
//    public Object getAllQuestions() {
//            QuestionServiceProto.GetQuestionsRequest request = QuestionServiceProto.GetQuestionsRequest.newBuilder().setCategoryName("World").build();
//            QuestionServiceProto.GetQuestionsResponse response = stub.getQuestionsForQuiz(request);
//            return response.getQuestionIdsList();
//    }