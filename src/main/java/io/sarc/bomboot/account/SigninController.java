package io.sarc.bomboot.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.sarc.bomboot.database.ApiLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "SigninController")
public class SigninController {
	private static Logger log = LoggerFactory.getLogger(SigninController.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ApiLog apiLog;

	private String api = "/v1/account/signin";

	@ApiOperation(value = "signin", notes = "Signin")
	@RequestMapping(value = "/v1/account/signin")
	public String signin() {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);
		
		int count = apiLog.get(api);
		apiLog.add(api, (count+1)+"");
		
		span.finish();

		return api;
	}
}