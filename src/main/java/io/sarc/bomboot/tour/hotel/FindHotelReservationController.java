package io.sarc.bomboot.tour.hotel;

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
@Api(value = "FindController")
public class FindHotelReservationController {
	private static Logger log = LoggerFactory.getLogger(FindHotelReservationController.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ApiLog apiLog;
	
	private String api = "/v1/tour/hotel/reservation/find";

	@ApiOperation(value = "findHotelReservation", notes = "FindHotelReservation")
	@RequestMapping(value = "/v1/tour/hotel/reservation/find/{reservationNumber}")
	public String findHotelReservation(@PathVariable("reservationNumber") String reservationNumber) {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);
	
		int count = apiLog.get(api);
		apiLog.add(api, reservationNumber + "_" + count);

		span.finish();

		return "<h2>" + api + "</h2>";
	}
}