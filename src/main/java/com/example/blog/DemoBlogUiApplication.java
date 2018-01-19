package com.example.blog;

import am.ik.marked4j.Marked;
import am.ik.marked4j.MarkedBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoBlogUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBlogUiApplication.class, args);
	}

	@Bean
	public Marked marked() {
		return new MarkedBuilder()
				.gfm(true)
				.build();
	}
}
