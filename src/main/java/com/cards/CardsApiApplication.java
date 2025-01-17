package com.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CardsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApiApplication.class, args);
	}

}
