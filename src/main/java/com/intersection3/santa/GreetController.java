package com.intersection3.santa;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
	
@RestController
@Api(value = "GreetController")
public class GreetController {
	private static Logger log = LoggerFactory.getLogger(GreetController.class);
	
	@Value("${startup.complete.code}")
	private String startup_complete_code;
	
    @Autowired
    private Tracer tracer;
	
	@ApiOperation(value = "greet", notes = "인사")
	@RequestMapping(value = "/v1/greet")
	public String greet() {
		Span span = tracer.buildSpan("GET /v1/greet").start();
		
		log.info("Access /v1/greet");
		log.info(startup_complete_code);
		
		List<String> greetings = Arrays.asList("Hi", "Hello", "Hallo", "Konnichiha", "Salut", "Greetings");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		
		span.finish();
		
		return greetings.get(randomNum);
	}
}