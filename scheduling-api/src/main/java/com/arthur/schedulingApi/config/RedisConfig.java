package com.arthur.schedulingApi.config;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RedisConfig {

    private final JacksonConfig jacksonConfig;

    @Bean("serviceResponseSerializer")
    public Jackson2JsonRedisSerializer<ServiceResponseDTO> serviceResponseSerializer() {
        Jackson2JsonRedisSerializer<ServiceResponseDTO> serializer = 
            new Jackson2JsonRedisSerializer<>(ServiceResponseDTO.class);
        serializer.setObjectMapper(jacksonConfig.redisObjectMapper());
        return serializer;
    }

    @Bean("userResponseSerializer")
    public Jackson2JsonRedisSerializer<UserResponseDTO> userResponseSerializer() {
        Jackson2JsonRedisSerializer<UserResponseDTO> serializer = 
            new Jackson2JsonRedisSerializer<>(UserResponseDTO.class);
        serializer.setObjectMapper(jacksonConfig.redisObjectMapper());
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

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder(connectionFactory)
                .withCacheConfiguration("SERVICE_CACHE", serviceCacheConfiguration())
                .withCacheConfiguration("USER_CACHE", userCacheConfiguration())
                .build();
    }
}