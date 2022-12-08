package com.peacework.routes

import com.peacework.domain.controller.ConversationController
import com.peacework.domain.controller.UserController
import com.peacework.domain.model.Conversation
import com.peacework.domain.model.Trail
import com.peacework.domain.ui.*
import com.peacework.plugins.AppPrincipal
import com.peacework.util.ComponentType
import com.peacework.util.TemplateFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Route.conversationRoutes(userController: UserController, conversationController: ConversationController) {
    route("/conversations") {
        post {
            try {
                val principal = call.principal<AppPrincipal>()
                val user = userController.getUserById(principal?.userId ?: "")
                val conversation = call.receive<Conversation>()

                conversationController.postConversation(conversation)
                call.respond(
                    TemplateFactory.createLoungePage(user, conversationController)
                )
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        delete("/{conversationId}") {
            try {
                val id = call.parameters["conversationId"].toString()
                conversationController.deleteConversation(id)
                call.respond(HttpStatusCode.OK,  "Successfully deleted conversation $id")
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        get {
            try {
                val id = call.parameters["userId"].toString()
                val conversations: List<Conversation> = conversationController.getConversationsByUserID(id)
                call.respond(HttpStatusCode.OK,  conversations)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

        put ("/trail/{conversationId}"){
            val params = call.receiveParameters()
            val conversationId = params["conversationId"].toString()
            val lastSentDate = params["lastSentDate"].toString()
            val userId = params["userId"].toString()
            val message = params["message"].toString()
            val isSeen = params["isSeen"].toBoolean()
            try {
                conversationController.updateTrail(
                    conversationId,
                    Trail(
                        userId = userId,
                        message = message,
                        lastSentDate = lastSentDate,
                        isSeen = isSeen
                    )
                )
                call.respond(HttpStatusCode.OK)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}