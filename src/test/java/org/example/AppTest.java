package org.example;

import io.restassured.RestAssured;

public class AppTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://reqres.in";

        String response = RestAssured
            .given()
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("page", "2")
            .when()
                .get("/api/users")
            .then()
                .log()
                .all()
            .assertThat()
                .statusCode(200)
            .extract()
                .body()
                .asString();

    }

}
