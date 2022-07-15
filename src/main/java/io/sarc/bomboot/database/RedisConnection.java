package io.sarc.bomboot.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.ApiOperation;
import redis.clients.jedis.Jedis;

public class RedisConnection {
	private static Logger log = LoggerFactory.getLogger(RedisConnection.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private JedisConnectionPool jedisConnectionPool;

	@Value("${httpc.baekdusan.connection}")
	private String httpcBaekdusanCopnnection;
	
	@Value("${httpc.cheonji.transportation}")
	private String httpcCheonjiTransporation;
	
	private String api = "/v1/cheonji/tour";
	 
	@ApiOperation(value = "tour", notes = "Tour")
	@RequestMapping(value = "/v1/cheonji/tour")
	public String httpConnection() {
		Span span = tracer.buildSpan("GET " + api).start();

		log.info("Access /v1/cheonji/tour");
		log.info(startup_complete_code);

		try (Jedis jedis = jedisConnectionPool.jedisPool.getResource()) {
			jedis.set(api, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		span.finish();

		return "<h2>" + api + "</h2>";
	}
}