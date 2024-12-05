package com.quangoi.question_service;

import com.quangoi.question_service.service.QuestionServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class QuestionServiceApplication {

	@Autowired
	private QuestionServiceImpl questionServiceImpl;

	@EventListener(ApplicationReadyEvent.class)
	public void startGrpcServer() {
		try {
			Server server = ServerBuilder.forPort(9991)
					.addService(questionServiceImpl)
					.build();
			server.start();
			log.info("gRPC server started on port 9991");
		} catch (Exception e) {
			log.error("Error starting gRPC server: {}", e.getMessage());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
