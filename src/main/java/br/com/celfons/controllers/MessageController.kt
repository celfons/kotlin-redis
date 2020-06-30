package br.com.celfons.controllers

import br.com.celfons.controllers.requests.MessageRequest
import br.com.celfons.controllers.responses.MessageResponse
import br.com.celfons.services.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/")
open class MessageController {

    @Autowired
    private lateinit var messageService: MessageService

    @RequestMapping(value = ["/"], method = [RequestMethod.GET], produces = [APPLICATION_STREAM_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    open fun findAll(): List<MessageResponse> = messageService.findAll()

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = [APPLICATION_STREAM_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    open fun findById(@PathVariable("id") id: Long): MessageResponse? = messageService.findById(id)

    @RequestMapping(value = ["/"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.ACCEPTED)
    open fun save(@RequestBody @Valid request: MessageRequest): MessageResponse = messageService.save(request)

}
