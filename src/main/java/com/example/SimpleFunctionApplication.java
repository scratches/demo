package com.example;

import java.util.function.Function;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class SimpleFunctionApplication
		implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		FunctionalSpringApplication.run(SimpleFunctionApplication.class, args);
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		context.registerBean("function", Function.class, () -> function());
		context.registerBean(FunctionRegistration.class,
				() -> new FunctionRegistration<>(context.getBean("function"), "function")
						.type(FunctionType.from(Integer.class).to(Integer.class)));
	}

	@Bean
	public Function<Integer, Integer> function() {
		return value -> 2 * value;
	}
}
