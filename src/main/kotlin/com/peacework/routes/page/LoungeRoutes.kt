package com.peacework.routes.page

import com.peacework.domain.controller.ConversationController
import com.peacework.domain.controller.UserController
import com.peacework.domain.model.*
import com.peacework.domain.ui.*
import com.peacework.plugins.AppPrincipal
import com.peacework.util.ComponentType.*
import com.peacework.util.TemplateFactory
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun Route.loungeRoutes(userController: UserController, conversationController: ConversationController) {
    route("/lounge") {
        get {
            val principal = call.principal<AppPrincipal>()

//            userController.postUser(
//                User(
//                    createdOn = "11/16/2022",
//                    fullname = "Mwai Banda",
//                    email = "mwaibanda@peacework.com",
//                    userId = principal?.userId ?: "",
//                    position = "Dev",
//                    dateStarted = "11/16/2022",
//                    company = "PeaceWork",
//                    contacts = listOf(
//                        Contact(userId = "", username = "Peter"),
//                        Contact(userId = "", username = "John"),
//                        Contact(userId = "", username = "James"),
//                        Contact(userId = "", username = "Solomon"),
//                        Contact(userId = "", username = "David")
//                    )
//                )
//            )
            val user = userController.getUserById(principal?.userId ?: "")
            call.respond(
                TemplateFactory.createLoungePage(user, conversationController)
            )
        }
    }
}
