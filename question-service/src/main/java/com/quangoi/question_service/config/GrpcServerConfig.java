package com.quangoi.question_service.config;

import com.quangoi.question_service.service.QuestionServiceImpl;
import com.quangoi.quiz_service.QuestionServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration

public class GrpcServerConfig extends QuestionServiceGrpc.QuestionServiceImplBase {


}

