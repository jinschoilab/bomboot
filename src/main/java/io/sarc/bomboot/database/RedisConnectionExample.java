package io.sarc.bomboot.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;

public class RedisConnectionExample {
	private static Logger log = LoggerFactory.getLogger(RedisConnectionExample.class);

	@Value("${startup.complete.code}")
	private String startup_complete_code;
	
	@Autowired
	private JedisConnectionPool jedisConnectionPool;
		 
	@RequestMapping(value = "/v1/cheonji/tour")
	public String httpConnection() {
		
		try (Jedis jedis = jedisConnectionPool.jedisPool.getResource()) {
			jedis.set("key", "value");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "RedisConnectionExample";
	}
}