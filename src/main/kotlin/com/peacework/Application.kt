package com.peacework

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.peacework.plugins.*

fun main() {

    embeddedServer(Netty, port = 8083, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureAuth()
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureTemplating()
    configureSerialization()
    configureSockets()
    configureRouting()
}
