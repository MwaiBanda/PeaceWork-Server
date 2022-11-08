package com.peacework.routes

import com.peacework.plugins.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.responseRoutes() {
    route("response") {
        get {
            val response = Response()
            call.respond(response)
            call.response.header(HttpHeaders.AccessControlAllowOrigin, "10.0.0.150:8083")
            call.response.header(HttpHeaders.AccessControlAllowCredentials, "true")
        }
    }
}