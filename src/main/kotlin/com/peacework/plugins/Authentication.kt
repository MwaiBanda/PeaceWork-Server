package com.peacework.plugins

import dev.forst.ktor.apikey.apiKey
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.util.*


data class AppPrincipal(
    val key: String,
    val userId: String,
    val sessionId: String
) : Principal

fun Application.configureAuth() {
    install(Authentication) {
        val expectedApiKey = "100000-10001"
        apiKey {
            validate { keyFromHeader ->
                val userId = request.header("X-User-Id") ?: "Guest"
                keyFromHeader
                    .takeIf { it == expectedApiKey }
                    ?.let { AppPrincipal(key = it, userId = userId, sessionId = generateNonce()) }
            }
        }
    }
}