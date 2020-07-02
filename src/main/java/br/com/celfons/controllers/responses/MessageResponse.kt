package br.com.celfons.controllers.responses

import java.io.Serializable

data class MessageResponse(
    var id: Long? = null,
    var value: String? = null
) : Serializable
