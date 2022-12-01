package com.infosys.spa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SinglePageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinglePageApplication.class, args);
	} 

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins(
					"http://localhost:3000"
				)
				.allowedMethods("DELETE", "GET", "HEAD", "OPTIONS", "POST", "PUT")
				.allowCredentials(true);
			}
		};
	}
	
}
