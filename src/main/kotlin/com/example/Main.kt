package com.example

import io.helidon.webserver.WebServer
import io.helidon.webserver.http.Handler
import io.helidon.webserver.http.HttpRouting
import io.helidon.webserver.http.ServerRequest
import io.helidon.webserver.http.ServerResponse
import java.util.function.Consumer

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val server: WebServer = WebServer.builder()
            .routing { routing(it) }  // pass the builder to your routing function
            .port(8080)
            .build()

        server.start()
        println("Server is running at http://localhost:" + server.port())
    }

    private fun routing(routing: HttpRouting.Builder) {
        routing.get(
            "/",
            Handler { req: ServerRequest?, res: ServerResponse? -> res?.send("Hello from Helidon 4!") })
    }
}