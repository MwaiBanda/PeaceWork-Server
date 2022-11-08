package com.peacework.routes.ui

import com.peacework.data.ui.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json



fun Route.homeRoutes() {
    route("home") {
        get {
            call.respond(
                Page(
                    pageTitle = "Home",
                    components = listOf(
                        Component(
                            type = "Image",
                            data = hashMapOf(
                                "imageURL" to "https://images.wallpapersden.com/image/download/the-mandalorian-cool_bGdsaW2UmZqaraWkpJRobWllrWdma2U.jpg"
                            )
                        ),
                        Component(
                            type = "VerticalList",
                            data = hashMapOf(
                                "column" to Json.encodeToString(
                                    VerticalList.serializer(),
                                    VerticalList(
                                        type = "VerticalList",
                                        items = listOf(
                                            ListItem(
                                                id = 1,
                                                name = "Mandalorian",
                                                imageURL = "https://images.wallpapersden.com/image/download/the-mandalorian-cool_bGdsaW2UmZqaraWkpJRobWllrWdma2U.jpg"
                                            ),
                                            ListItem(
                                                id = 2,
                                                name = "Panther",
                                                imageURL = "https://wallpapers.com/images/hd/cool-picture-black-panther-f71mqdcoz9g5ouc4-f71mqdcoz9g5ouc4.jpg"
                                            ),
                                            ListItem(
                                                id = 3,
                                                name = "NewEvolution",
                                                imageURL = "https://newevolutiondesigns.com/images/freebies/cool-4k-ipad-wallpaper-7.jpg"
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