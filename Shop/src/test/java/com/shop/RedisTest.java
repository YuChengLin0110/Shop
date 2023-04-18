package com.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class RedisTest {
	
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void testRedis() {
		String key = "TestKey";
		String value = "TestValue";
		redisTemplate.opsForValue().set(key, value);
		String result = redisTemplate.opsForValue().get(key);
		assertEquals(value,result);

	}

}

