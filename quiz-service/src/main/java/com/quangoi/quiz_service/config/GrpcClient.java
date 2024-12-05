package com.quangoi.quiz_service.config;

import com.quangoi.quiz_service.QuestionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
//import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class GrpcClient  {
    @Bean
    public ManagedChannel channel() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9991).usePlaintext().build();
        return channel;
    }


    @Bean
    public QuestionServiceGrpc.QuestionServiceBlockingStub questionServiceBlockingStub() {
        QuestionServiceGrpc.QuestionServiceBlockingStub stub = QuestionServiceGrpc.newBlockingStub(channel());
    return stub;
    }



}
