package com.peacework.routes.ui

import com.peacework.domain.controller.UserController
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
                    pageTitle = "Lounge",
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
                    )
                )
            )
        }
    }
}
