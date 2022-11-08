package com.peacework.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*

fun Application.configureHTTP() {
    install(CORS) {
        allowHost("10.0.0.150:8080")
        allowHost("client-host")
        allowHost("client-host:8080")
        allowHost("client-host", schemes = listOf("http", "https"))
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.Referrer)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        allowNonSimpleContentTypes = true
        allowCredentials = true
        allowSameOrigin = true
        anyHost()
    }
    install(DefaultHeaders) {
        header(HttpHeaders.AccessControlAllowOrigin, "http://10.0.0.150:8080")

        header(HttpHeaders.AccessControlAllowCredentials, "true")
        header(HttpHeaders.Origin, "http://10.0.0.150:8080")


    }
}
