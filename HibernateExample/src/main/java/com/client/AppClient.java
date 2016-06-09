package com.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.ApplicationConfig;
import com.service.UserService;

public class AppClient {

	public static void main(String... str) {
		// Load application context Configuration using annotation.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				ApplicationConfig.class);

		UserService userService = context.getBean(UserService.class);
		
		userService.performOperation();
		
		
		// Close context.
		context.close();
	}
}
