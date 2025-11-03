package com.at.t.eCommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SessionConfig {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(
    		  @Value("${spring.data.redis.host}") String host,
    		  @Value("${spring.data.redis.port}") int port) {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }
}

