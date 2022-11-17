package com.peacework.plugins

import com.peacework.domain.controller.UserController
import com.peacework.routes.responseRoutes
import com.peacework.routes.ui.loungeRoutes
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
data class Response(val response: List<String> = listOf("This is a response"))

fun Application.configureRouting() {

    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }

    }


    routing {
        authenticate {
            val userController: UserController by inject()
            loungeRoutes(userController = userController)
            responseRoutes()
            // Static plugin. Try to access `/static/index.html`
            static("/static") {
                resources("static")
            }
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
