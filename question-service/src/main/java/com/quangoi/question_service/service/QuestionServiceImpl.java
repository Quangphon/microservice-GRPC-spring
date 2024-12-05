package com.quangoi.question_service.service;

import com.quangoi.question_service.dao.QuestionDao;
import com.quangoi.question_service.model.Question;
import com.quangoi.quiz_service.QuestionServiceGrpc;
import com.quangoi.quiz_service.QuestionServiceProto.GetQuestionsRequest;
import com.quangoi.quiz_service.QuestionServiceProto.GetQuestionsResponse;
import com.quangoi.quiz_service.QuestionServiceProto.GetQuestsRequest;
import com.quangoi.quiz_service.QuestionServiceProto.GetQuestsResponse;
import com.quangoi.quiz_service.QuestionServiceProto.QuestionWrapper;
import com.quangoi.quiz_service.QuestionServiceProto.GetScoreRequest;
import com.quangoi.quiz_service.QuestionServiceProto.GetScoreResponse;
import com.quangoi.quiz_service.QuestionServiceProto.Response;
//import io.grpc.stub.StreamObserver;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@GrpcService: Annotation tích hợp Spring Boot với gRPC, giúp tự động đăng ký service này với server gRPC.
//@GrpcService
@Service
public class QuestionServiceImpl extends QuestionServiceGrpc.QuestionServiceImplBase {

    @Autowired
    QuestionDao questionDao;

//    StreamObserver: Interface dùng để gửi dữ liệu qua lại giữa client và server gRPC.
    @Override
    public void getQuestionsForQuiz(GetQuestionsRequest request, StreamObserver<GetQuestionsResponse> responseObserver) {
//        List<Integer> questionIds = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(
                request.getCategoryName(),
                request.getNumQuestions());

        GetQuestionsResponse response = GetQuestionsResponse.newBuilder()
                .addAllQuestionIds(questionIds)
                .build();
//         Gửi dữ liệu trả về client
        responseObserver.onNext(response);
//        Đóng stream sau khi hoàn thành
        responseObserver.onCompleted();
    }

    @Override
    public void getQuestsFromId(GetQuestsRequest request, StreamObserver<GetQuestsResponse> responseObserver) {
        // Thực thi logic của phương thức
        List<QuestionWrapper> questionsWrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (int id : request.getQuestionIdsList()) {
            Question q = questionDao.findById(id).get();
            questions.add(q);
        }

        for (Question q : questions) {
            questionsWrapper.add(QuestionWrapper.newBuilder()
                    .setId(q.getId())
                    .setQuestion(q.getQuestionTitle())
                    .addOptions(q.getOption1())
                    .addOptions(q.getOption2())
                    .addOptions(q.getOption3())
                    .addOptions(q.getOption4())
                    .setCorrectOption(q.getRightAnswer())
                    .build());
        }
        GetQuestsResponse response = GetQuestsResponse.newBuilder()
                .addAllQuestions(questionsWrapper)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getScore(GetScoreRequest request, StreamObserver<GetScoreResponse> responseObserver) {
        int score = 0;
        List<Response> responses = new ArrayList<>();

            for (Response response : request.getResponsesList()) {
                Question question = questionDao.findById(response.getQuestionId()).get();
            if (response.getSelectedOption() == question.getRightAnswer()) {
                score++;
            }
        }

        GetScoreResponse getScoreResponse = GetScoreResponse.newBuilder()
                .setScore(score)
                .build();

        responseObserver.onNext(getScoreResponse);
        responseObserver.onCompleted();
    }
}
