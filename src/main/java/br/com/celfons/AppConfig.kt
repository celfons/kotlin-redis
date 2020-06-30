package br.com.celfons

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.StringRedisSerializer


@SpringBootApplication
@EnableCaching
open class AppConfig {
    @Bean
    @Primary
    open fun defaultCacheConfig(objectMapper: ObjectMapper?): RedisCacheConfiguration? {
        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer(objectMapper)))
    }
}

fun main(args: Array<String>) {
    runApplication<AppConfig>(*args)
}
