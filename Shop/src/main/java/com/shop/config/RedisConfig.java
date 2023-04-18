package com.shop.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.shop.Model.ProductBean;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, ProductBean> productRedisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, ProductBean> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		
		// 序列化
		RedisSerializer<?> stringSerializer = new StringRedisSerializer();
		
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = createJackson2JsonRedisSerializer();
		redisTemplateSerializer(redisTemplate, stringSerializer, jackson2JsonRedisSerializer);
		
		return redisTemplate;
	}

	@Bean
	public RedisTemplate<String, List<ProductBean>> productRedisListTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, List<ProductBean>> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		
		// 序列化
		RedisSerializer<?> stringSerializer = new StringRedisSerializer();
		
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = createJackson2JsonRedisSerializer();
		redisTemplateSerializer(redisTemplate, stringSerializer, jackson2JsonRedisSerializer);

		return redisTemplate;
	}

	// 序列化
	private Jackson2JsonRedisSerializer<Object> createJackson2JsonRedisSerializer() {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
				JsonTypeInfo.As.PROPERTY);
		
		jackson2JsonRedisSerializer.setObjectMapper(om);
		return jackson2JsonRedisSerializer;
	}

	private void redisTemplateSerializer(RedisTemplate<?, ?> redisTemplate, RedisSerializer<?> stringSerializer,
			Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
		// key序列化
		redisTemplate.setKeySerializer(stringSerializer);
		// value序列化
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		// Hash key序列化
		redisTemplate.setHashKeySerializer(stringSerializer);
		// Hash value序列化
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
		
		redisTemplate.afterPropertiesSet();
	}
}
