package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredStandalone {
    public static void main(String[] args) {
        // Example: Simple GET request
        Response response = RestAssured
                .given()
                .get("https://jsonplaceholder.typicode.com/posts/1");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Example: Simple POST request
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        Response postResponse = RestAssured
                .given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .extract().response();

        System.out.println("POST Status Code: " + postResponse.getStatusCode());
        System.out.println("POST Response Body: " + postResponse.getBody().asString());
    }
}