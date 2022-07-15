package io.sarc.bomboot.tour.air;

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
@Api(value = "SkyTeamController")
public class SkyteamController {
	private static Logger log = LoggerFactory.getLogger(SkyteamController.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ApiLog apiLog;
	
	private String api = "/v1/tour/air/skyteam";

	@ApiOperation(value = "skyteam", notes = "Skyteam")
	@RequestMapping(value = "/v1/tour/air/skyteam")
	public String skyteam() {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);
	
		apiLog.add(api, "");
	
		span.finish();

		return "<h2>" + api + "</h2>";
	}
}