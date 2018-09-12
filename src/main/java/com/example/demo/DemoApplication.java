package com.example.demo;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public Function<Message<String>, Message<String>> upper() {
		return value -> MessageBuilder.withPayload(value.getPayload().toUpperCase())
				.copyHeaders(value.getHeaders()).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
