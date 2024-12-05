package com.quangoi.quiz_service.service;


import com.quangoi.quiz_service.QuestionServiceGrpc;
import com.quangoi.quiz_service.QuestionServiceProto;
import com.quangoi.quiz_service.model.Answer;
import com.quangoi.quiz_service.model.QuestionWrappers;
import com.quangoi.quiz_service.model.QuizDto;
import com.quangoi.quiz_service.model.Response;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizServiceImpl {

//    @GrpcClient("GLOBAL")
    @Autowired
    private QuestionServiceGrpc.QuestionServiceBlockingStub questionServiceBlockingStub;


    public List<Integer> getQuestionsForQuiz(String categoryName, Integer numQuestions) {

        QuestionServiceProto.GetQuestionsRequest request = QuestionServiceProto.GetQuestionsRequest
                .newBuilder()
                .setCategoryName(categoryName)
                .setNumQuestions(numQuestions)
                .build();
        QuestionServiceProto.GetQuestionsResponse response = questionServiceBlockingStub.getQuestionsForQuiz(request);

        // Lấy danh sách questionIds từ response
        List<Integer> questionIds = new ArrayList<>(response.getQuestionIdsList());

        return questionIds;
    }

    public List<QuestionWrappers> getQuizQuestions(List<Integer> ids) {
        QuestionServiceProto.GetQuestsRequest request = QuestionServiceProto.GetQuestsRequest
                .newBuilder()
                .addAllQuestionIds(ids)
                .build();
        QuestionServiceProto.GetQuestsResponse response = questionServiceBlockingStub.getQuestsFromId(request);

        List<QuestionWrappers> questionWrappers = new ArrayList<>();

        for (QuestionServiceProto.QuestionWrapper questionWrapperProto : response.getQuestionsList()) {
            QuestionWrappers question = new QuestionWrappers();

            question.setId(questionWrapperProto.getId());
            question.setQuestion(questionWrapperProto.getQuestion());
            question.setOptions(questionWrapperProto.getOptionsList());
            question.setCorrectOption(questionWrapperProto.getCorrectOption());

            questionWrappers.add(question);
        }

        return questionWrappers;
    }

    public Integer calculateResult(List<Answer> answers) {
        List<QuestionServiceProto.Response> responses = new ArrayList<>();

        //Thêm từng câu vào
        for (Answer answer : answers) {
            QuestionServiceProto.Response res = QuestionServiceProto.Response
                    .newBuilder()
                    .setQuestionId(answer.getQuestionId())
                    .setSelectedOption(answer.getSelectedOption())
                    .build();
            responses.add(res);
        }

        QuestionServiceProto.GetScoreRequest checkResponse = QuestionServiceProto.GetScoreRequest
                .newBuilder()
                .addAllResponses(responses)
                .build();

        QuestionServiceProto.GetScoreResponse Score = questionServiceBlockingStub.getScore(checkResponse);

        Integer score = Score.getScore();
        return score;
    }


}
