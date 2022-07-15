package io.sarc.bomboot.tour.support;

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
@Api(value = "EmailController")
public class EmailContoller {
	private static Logger log = LoggerFactory.getLogger(EmailContoller.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	private String api = "/v1/tour/support/email";
	
	@ApiOperation(value = "email", notes = "Email")
	@RequestMapping(value = "/v1/tour/support/email")
	public String email() {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);
	
		span.finish();

		return "<h2>" + api + "</h2>";
	}
}