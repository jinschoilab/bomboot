package com.intersection3.santa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SantaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantaApplication.class, args);
		
		System.out.println("third");
	}
}