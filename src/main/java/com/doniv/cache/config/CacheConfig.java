package com.doniv.cache.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.embedded.RedisServer;

@Configuration
public class CacheConfig {

	
	private RedisServer redisServer;
	
	public CacheConfig() {
        this.redisServer = new RedisServer(6370);
    }
 
    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }
 
    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
	
	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		return config.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}


}