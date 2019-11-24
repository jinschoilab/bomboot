package com.intersection3.santa;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SantaApplication {	
	private static Logger log = LoggerFactory.getLogger(SantaApplication.class);
	
	@RequestMapping(value = "/greeting")
	public String greet() {
		log.info("Access /greeting");

		List<String> greetings = Arrays.asList("Hi", "Hello", "Hallo", "Salut", "Greetings");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		return greetings.get(randomNum);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SantaApplication.class, args);
		System.out.println("13");
	}
}