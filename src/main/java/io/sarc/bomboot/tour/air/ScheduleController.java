package io.sarc.bomboot.tour.air;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.sarc.bomboot.database.ApiLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "ScheduleController")
public class ScheduleController {
	private static Logger log = LoggerFactory.getLogger(ScheduleController.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ApiLog apiLog;
	
	private String api = "/v1/tour/air/schedule";

	@ApiOperation(value = "schedule", notes = "Schedule")
	@RequestMapping(value = "/v1/tour/air/schedule/{departure}/{arrive}/{ddate}/{adate}")
	public String schedule(@PathVariable("departure") String departure, @PathVariable("arrive") String arrive, @PathVariable("ddate") String ddate, @PathVariable("adate") String adate) {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);

		int count = apiLog.get(api);
		apiLog.add(api, departure + "_" + arrive + "_" + ddate + "_" + adate + "_" + count);
		
		span.finish();

		return "<h2>" + api + "</h2>";
	}
}