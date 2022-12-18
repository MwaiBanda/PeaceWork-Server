package com.peacework

import com.peacework.di.controllerModule
import com.peacework.di.dataSourceModule
import com.peacework.di.mainModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.peacework.plugins.*
import org.koin.ktor.plugin.Koin

fun main() {

    embeddedServer(Netty, port = 8083, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(Koin) {
        modules(
            listOf(
                mainModule,
                dataSourceModule,
                controllerModule
            )
        )

    }
    configureHTTP()
    configureAuth()
    configureSecurity()
    configureMonitoring()
    configureTemplating()
    configureSerialization()
    configureSockets()
    configureRouting()
}
