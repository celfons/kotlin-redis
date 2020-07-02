package br.com.celfons

import br.com.celfons.controllers.responses.MessageResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair


@SpringBootApplication
@EnableCaching
open class AppConfig {
    @Bean
    @Primary
    open fun defaultCacheConfig(): RedisCacheConfiguration? {
        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(SerializationPair.fromSerializer(JdkSerializationRedisSerializer()))
    }
}

fun main(args: Array<String>) {
    runApplication<AppConfig>(*args)
}
