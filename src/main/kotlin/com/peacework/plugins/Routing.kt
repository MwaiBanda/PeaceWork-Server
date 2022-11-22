package com.peacework.plugins

import com.peacework.domain.controller.ConversationController
import com.peacework.domain.controller.UserController
import com.peacework.routes.conversationRoutes
import com.peacework.routes.page.authControllerRoutes
import com.peacework.routes.page.loungeRoutes
import com.peacework.routes.page.pageRoutes
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject


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
            val conversationController: ConversationController by inject()

            pageRoutes(
                userController = userController,
                conversationController = conversationController
            )
            authControllerRoutes()
            conversationRoutes(
                userController = userController,
                conversationController = conversationController
            )
        }
        static("/static") {
            resources("static")
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
