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
@Api(value = "FindAirReservationController")
public class FindAirReservationController {
	private static Logger log = LoggerFactory.getLogger(FindAirReservationController.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ApiLog apiLog;
	
	@Value("${httpc.common.tour.reservation}")
	private String httpcCommonTourReservation;
	
	private String api = "/v1/tour/air/reservation/find";

	@ApiOperation(value = "findAirReservation", notes = "FindAirReservation")
	@RequestMapping(value = "/v1/tour/air/reservation/find/{reservationNumber}")
	public String findAirReservation(@PathVariable("reservationNumber") String reservationNumber) {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);
				
		int count = apiLog.get(api);
		apiLog.add(api, reservationNumber+"_"+count);
		
		span.finish();

		return "<h2>" + api + "</h2>";
	}
}