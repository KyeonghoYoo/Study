package me.kyeongho.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CacheConfig {

	private final RedisConnectionFactory connectionFactory;
	
	@Bean
	public CacheManager redisCacheManager() {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
						.serializeKeysWith(
								RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
						.serializeValuesWith(
								RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
						.entryTtl(Duration.ofMinutes(10l));
		
		RedisCacheManager redisCacheManager = RedisCacheManagerBuilder
						.fromConnectionFactory(connectionFactory)
						.cacheDefaults(redisCacheConfiguration)
						.build();
		return redisCacheManager;
	}
	
}
