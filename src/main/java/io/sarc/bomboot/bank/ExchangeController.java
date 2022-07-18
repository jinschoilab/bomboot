package io.sarc.bomboot.bank;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.sarc.bomboot.util.UrlConnection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Exchange")
public class ExchangeController {
	private static Logger log = LoggerFactory.getLogger(ExchangeController.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	private String api = "/v1/bank/exchange";
	
	private String exchangeApiPrefix = "https://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.";

	@ApiOperation(value = "exchange", notes = "Exchange")
	@RequestMapping(value = "/v1/bank/exchange/{from}/{to}")
	public String exchange(@PathVariable("from") String from, @PathVariable("to") String to) {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access " + api);
		log.info(startup_complete_code);
		
		String result = UrlConnection.getHttpConnectionResult(exchangeApiPrefix + from.toUpperCase() + to.toUpperCase());
		
		span.finish();

		return result;
	}
	
	public float basePrice(String jsonString) {
		System.out.println("jsonString="+jsonString);
		
		float basePrice = 0F;
		
		JSONArray jsonarray = new JSONArray(jsonString);
		
		for (int i=0; i<jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			basePrice = jsonobject.getFloat("basePrice");
		}
		return basePrice;		
	}
}