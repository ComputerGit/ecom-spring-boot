package com.at.t.eCommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//@Configuration
public class RedisConfig {

//	@Bean
//	public RedisConnectionFactory redisConnectionFactory() {
//
//		return new LettuceConnectionFactory();
//	}
//
//	RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//
//		RedisTemplate<String, String> tpl = new RedisTemplate<String, String>();
//		tpl.setConnectionFactory(cf);
//		tpl.afterPropertiesSet();
//
//		return tpl;
//
//	}
}
