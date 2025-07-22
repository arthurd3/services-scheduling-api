package com.arthur.schedulingApi.config;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean("redisObjectMapper")
    public ObjectMapper redisObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        return objectMapper;
    }

    @Bean("serviceResponseSerializer")
    public Jackson2JsonRedisSerializer<ServiceResponseDTO> serviceResponseSerializer() {
        Jackson2JsonRedisSerializer<ServiceResponseDTO> serializer = 
            new Jackson2JsonRedisSerializer<>(ServiceResponseDTO.class);
        serializer.setObjectMapper(redisObjectMapper());
        return serializer;
    }

    @Bean("userResponseSerializer")
    public Jackson2JsonRedisSerializer<UserResponseDTO> userResponseSerializer() {
        Jackson2JsonRedisSerializer<UserResponseDTO> serializer = 
            new Jackson2JsonRedisSerializer<>(UserResponseDTO.class);
        serializer.setObjectMapper(redisObjectMapper());
        return serializer;
    }

    @Bean("genericSerializer")
    public Jackson2JsonRedisSerializer<Object> genericSerializer() {
        Jackson2JsonRedisSerializer<Object> serializer = 
            new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(redisObjectMapper());
        return serializer;
    }

    @Bean("serviceCacheConfig")
    public RedisCacheConfiguration serviceCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(SerializationPair.fromSerializer(serviceResponseSerializer()))
                .disableCachingNullValues();
    }

    @Bean("userCacheConfig")
    public RedisCacheConfiguration userCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(SerializationPair.fromSerializer(userResponseSerializer()))
                .disableCachingNullValues();
    }

    @Bean("genericCacheConfig")
    public RedisCacheConfiguration genericCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(SerializationPair.fromSerializer(genericSerializer()))
                .disableCachingNullValues();
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(genericCacheConfiguration())
                .withCacheConfiguration("SERVICE_CACHE", serviceCacheConfiguration())
                .withCacheConfiguration("USER_CACHE", userCacheConfiguration()) 
                .build();
    }
}