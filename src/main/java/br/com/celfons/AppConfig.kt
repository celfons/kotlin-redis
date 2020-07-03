package br.com.celfons

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair

@SpringBootApplication
@EnableCaching
open class AppConfig {
    @Bean
    open fun defaultCacheConfig(): RedisCacheConfiguration? = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(SerializationPair.fromSerializer(JdkSerializationRedisSerializer()))
}

fun main(args: Array<String>) {
    runApplication<AppConfig>(*args)
}
