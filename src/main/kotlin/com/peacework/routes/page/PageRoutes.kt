package com.peacework.routes.page

import com.peacework.domain.controller.ConversationController
import com.peacework.domain.controller.UserController
import io.ktor.server.routing.*

fun Route.pageRoutes(userController: UserController, conversationController: ConversationController){
        loungeRoutes(userController = userController, conversationController = conversationController)
        authControllerRoutes()
}