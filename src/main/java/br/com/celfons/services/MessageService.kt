package br.com.celfons.services

import br.com.celfons.controllers.requests.MessageRequest
import br.com.celfons.controllers.responses.MessageResponse
import br.com.celfons.domains.Message
import br.com.celfons.repositories.MessageRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import khttp.get

@Service
open class MessageService {

    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Cacheable(cacheNames = ["Message"], key = "#root.method.name") // REDIS = Message::findAll
    open fun findAll(): List<MessageResponse> = messageRepository.findAll().stream()
        .map { message -> MessageResponse(message.id, message.value) }
        .collect(Collectors.toList())

    @Cacheable(cacheNames = ["Message"], key = "#id") // REDIS = Message::001, Message::002, ...
    open fun findById(id: Long): MessageResponse? = messageRepository.findById(id)
        .map {message -> MessageResponse(message.id, message.value) }.orElse(MessageResponse())

    @CacheEvict(cacheNames = ["Message"], allEntries = true) // REDIS = reset all cache after insert new data
    open fun save(request: MessageRequest): MessageResponse = messageRepository.save(Message(request.id, request.value))
        .run { MessageResponse(id, value) }

    @Cacheable(cacheNames = ["Message"], key = "#id", sync = true)
    open fun getExternal(id: Long): MessageResponse? =
        ObjectMapper().readValue(get("http://0.0.0.0:8883/").content, MessageResponse::class.java)

}
