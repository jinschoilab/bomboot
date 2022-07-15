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
public class FindHotelController {
	private static Logger log = LoggerFactory.getLogger(FindHotelController.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ApiLog apiLog;
	
	private String api = "/v1/tour/hotel/find";

	@ApiOperation(value = "findHotel", notes = "FindHotel")
	@RequestMapping(value = "/v1/tour/hotel/find/{destination}/{sdate}/{edate}")
	public String findHotel(@PathVariable("destination") String destination, @PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);
			
		int count = apiLog.get(api);
		apiLog.add(api, destination + "_" + sdate + "_" + edate + "_" + count);

		span.finish();

		return "<h2>" + api + "</h2>";
	}
}