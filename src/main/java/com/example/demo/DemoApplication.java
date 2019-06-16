package com.example.demo;

import java.util.function.Function;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class DemoApplication implements Function<Message<String>, Message<String>> {

	@Override
	public Message<String> apply(Message<String> value) {
		return MessageBuilder.withPayload(value.getPayload().toUpperCase())
				.copyHeaders(value.getHeaders()).build();
	}

	public static void main(String[] args) {
		FunctionalSpringApplication.run(DemoApplication.class, args);
	}
}
