package com.peacework.routes.page

import com.peacework.domain.ui.ActionComponent
import com.peacework.domain.ui.ActionItemHolder
import com.peacework.domain.ui.Component
import com.peacework.domain.ui.PageComponent
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.netty.handler.codec.http.HttpResponse
import kotlinx.serialization.json.Json

fun Route.authControllerRoutes() {
    route("auth-controller") {
        get {
            call.respond(
                PageComponent(
                    title = "Auth Controller",
                    components = listOf(
                        Component(
                            type = "BannerLogo",
                            data = hashMapOf(
                                "imageURL" to "http://10.0.0.150:8083/static/img/peacework-large.png"
                            )
                        ),
                        Component(
                            type = "VerticalList",
                            data = hashMapOf(
                                "itemType" to "Button",
                                "items" to Json.encodeToString(
                                    ActionItemHolder.serializer(), ActionItemHolder(
                                        items = listOf(
                                            ActionComponent(
                                                id = "0",
                                                data = hashMapOf(
                                                    "fill" to "Filled",
                                                    "text" to "Login",
                                                    "type" to "Navigation",
                                                    "destination" to "login",
                                                )
                                            ),
                                            ActionComponent(
                                                id = "1",
                                                data = hashMapOf(
                                                    "fill" to "Outlined",
                                                    "text" to "Signup",
                                                    "action" to "Navigation",
                                                    "destination" to "signup"
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}