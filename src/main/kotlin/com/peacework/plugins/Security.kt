package com.peacework.plugins

import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<AppPrincipal>("session") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    routing {
        intercept(ApplicationCallPipeline.Plugins) {
            if (
                call.sessions.get<AppPrincipal>() == null ||
                call.sessions.get<AppPrincipal>()?.userId == "Guest" ||
                call.sessions.get<AppPrincipal>()?.userId != call.request.header("User-Id")
            ) {
                call.request.header("X-Api-key")?.let {
                    if (it.isNotEmpty()) {
                        call.sessions.set(
                            AppPrincipal(
                                key = it,
                                userId = call.request.header("X-User-Id") ?: "Guest",
                                deviceType = call.request.header("X-Device-Type").toString(),
                                sessionId = generateNonce()
                            )
                        )
                    }
                }
            }
        }
    }
}
