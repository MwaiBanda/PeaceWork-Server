package com.peacework.routes

import com.peacework.domain.controller.ConversationController
import com.peacework.domain.controller.UserController
import com.peacework.domain.model.Conversation
import com.peacework.domain.model.Trail
import com.peacework.domain.ui.*
import com.peacework.plugins.AppPrincipal
import com.peacework.util.ComponentType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Route.conversationRoutes(userController: UserController, conversationController: ConversationController) {
    route("conversations") {
        post {
            val principal = call.principal<AppPrincipal>()
            val user = userController.getUserById(principal?.userId ?: "")

            try {
                val conversation = call.receive<Conversation>()
                conversationController.postConversation(conversation)
                call.respond(
                    PageComponent(
                        title = "Lounge",
                        components = listOf(
                            Component(
                                type = ComponentType.WelcomeBanner.name,
                                data = hashMapOf(
                                    "heading" to "Hello",
                                    "subheading" to user.fullname.split(" ").first()
                                )
                            ),
                            Component(type = ComponentType.Divider.name),
                            Component(
                                type = "ActionRow",
                                data = hashMapOf(
                                    "text" to "Tap Actions to Expand & Filter Contacts",
                                    "buttons" to Json.encodeToString(
                                        ActionItemHolder.serializer(),
                                        ActionItemHolder(
                                            listOf(
                                                ActionComponent(
                                                    id = "0",
                                                    data = hashMapOf(
                                                        "action" to "expand",
                                                        "icon" to Json.encodeToString(
                                                            IconPack.serializer(),
                                                            IconPack(
                                                                icons = hashMapOf(
                                                                    "iOS" to "slider.horizontal.below.rectangle"
                                                                )
                                                            )
                                                        )
                                                    )
                                                ),
                                                ActionComponent(
                                                    id = "1",
                                                    data = hashMapOf(
                                                        "action" to "filter",
                                                        "icon" to Json.encodeToString(
                                                            IconPack.serializer(),
                                                            IconPack(
                                                                icons = hashMapOf(
                                                                    "iOS" to "slider.horizontal.3"
                                                                )
                                                            )
                                                        )
                                                    )
                                                ),
                                            )
                                        )
                                    )
                                )
                            ),
                            Component(
                                type = ComponentType.HorizontalList.name,
                                data = hashMapOf(
                                    "itemType" to "Contact",
                                    "items" to Json.encodeToString(
                                        ContactItemHolder.serializer(),
                                        ContactItemHolder(user.contacts)
                                    )
                                )
                            ),
                            Component(type = ComponentType.Divider.name),
                            Component(
                                type = "SectionRow", data = hashMapOf(
                                    "heading" to "Messages",
                                    "subheading" to "Recents",
                                    "action" to Json.encodeToString(
                                        ActionComponent.serializer(), ActionComponent(
                                            id = "0",
                                            data = hashMapOf(
                                                "action" to "filter",
                                                "icon" to Json.encodeToString(
                                                    IconPack.serializer(),
                                                    IconPack(
                                                        icons = hashMapOf(
                                                            "iOS" to "slider.horizontal.3"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            ),
                            Component(type = ComponentType.Divider.name),
                            Component(
                                type = "VerticalList",
                                data = hashMapOf(
                                    "itemType" to "Conversation",
                                    "items" to Json.encodeToString(
                                        ConversationItemHolder.serializer(),
                                        ConversationItemHolder(items = conversationController.getConversationsByUserID(user.userId))
                                    )
                                )
                            )
                        )
                    )
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