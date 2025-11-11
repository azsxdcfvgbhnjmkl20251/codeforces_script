package com.example;

import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

public class Main {
    public static void main(String[] args) {
        WebServer server = WebServer.builder()
                .routing(Main::routing)
                .port(8080)
                .build();

        server.start();
        System.out.println("Server is running at http://localhost:" + server.port());
    }

    private static void routing(HttpRouting.Builder routing) {
        routing.get("/", (req, res) -> res.send("Hello from Helidon 4!"));
    }
}
