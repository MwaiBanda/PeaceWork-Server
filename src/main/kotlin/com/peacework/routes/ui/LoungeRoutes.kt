package com.peacework.routes.ui

import com.peacework.domain.controller.UserController
import com.peacework.domain.model.Conversation
import com.peacework.domain.model.Participant
import com.peacework.domain.model.Trail
import com.peacework.domain.ui.*
import com.peacework.plugins.AppPrincipal
import com.peacework.util.ComponentType
import com.peacework.util.ComponentType.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun Route.loungeRoutes(userController: UserController) {
    route("lounge") {
        get {
            val principal = call.principal<AppPrincipal>()
            /*
            userController.postUser(
                User(
                    createdOn = "11/16/2022",
                    fullname = "Mwai Banda",
                    email = "mwaibanda@peacework.com",
                    userId = user?.userId ?: "",
                    position = "Dev",
                    dateStarted = "11/16/2022",
                    company = "PeaceWork",
                    contacts = listOf(
                        Contact(userId = "", username = "Peter"),
                        Contact(userId = "", username = "John"),
                        Contact(userId = "", username = "James"),
                        Contact(userId = "", username = "Solomon"),
                        Contact(userId = "", username = "David")
                    )
                )
            ) */
            val user = userController.getUserById(principal?.userId ?: "")
            call.respond(
                PageComponent(
                    title = "Lounge",
                    components = listOf(
                        Component(
                            type = WelcomeBanner.name,
                            data = hashMapOf(
                                "header" to "Hello",
                                "subtitle" to user.fullname.split(" ").first()
                            )
                        ),
                        Component(type = Divider.name),
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
                                            ))
                                        ),
                                        ActionComponent(
                                            id = "1",
                                            data = hashMapOf(
                                                "action" to "filter",
                                                "icon" to   Json.encodeToString(
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
                        )),
                        Component(
                            type = HorizontalList.name,
                            data = hashMapOf(
                                "itemType" to "Contact",
                                "items" to Json.encodeToString(
                                    ContactItemHolder.serializer(),
                                    ContactItemHolder(user.contacts)
                                )
                            )
                        ),
                        Component(type = Divider.name),
                        Component(type = "SectionRow", data = hashMapOf(
                            "heading" to "Messages",
                            "subheading" to "Recents",
                            "action" to Json.encodeToString(ActionComponent.serializer(), ActionComponent(
                                id = "0",
                                data = hashMapOf(
                                    "action" to "filter",
                                    "icon" to Json.encodeToString(
                                        IconPack.serializer(),
                                        IconPack(icons = hashMapOf(
                                            "iOS" to "slider.horizontal.3"
                                        ))
                                    )
                                )
                            ))
                        )),
                        Component(type = Divider.name),
                        Component(
                            type = "VerticalList",
                            data = hashMapOf(
                                "itemType" to "Message",
                                "items" to Json.encodeToString(
                                    ConversationItemHolder.serializer(),
                                    ConversationItemHolder(items = listOf(
                                        Conversation(
                                            participants = listOf(Participant("002", "John Wick")),
                                            trail = Trail("002", "Where's my dog?", "11/18/2022", false),
                                            timestamp = System.currentTimeMillis()
                                        )
                                    ))
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}
