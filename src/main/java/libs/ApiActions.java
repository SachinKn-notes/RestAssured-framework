package libs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.User;

import java.util.Map;

public class ApiActions {
    private int driverNumber;

    public int getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(int driverNumber) {
        this.driverNumber = driverNumber;
    }

    public Response get(RequestSpecification reqSpec, String endPoint) {
        return RestAssured
            .given()
                .log().all()
                .spec(reqSpec)
            .when()
                .get(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(200)
            .extract()
                .response();
    }

    public Response get(RequestSpecification reqSpec, String endPoint, Map<String, Object> queryParams) {
        return RestAssured
            .given()
                .log().all()
                .spec(reqSpec)
                .queryParams(queryParams)
            .when()
                .get(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(200)
            .extract()
                .response();
    }

    public <T> T get(RequestSpecification reqSpec, String endPoint, Class<T> pojoType) {
        return RestAssured
            .given()
                .log().all()
                .spec(reqSpec)
            .when()
                .get(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(200)
            .extract()
                .as(pojoType);
    }

    public <T> T get(RequestSpecification reqSpec, String endPoint, Map<String, Object> queryParams, Class<T> pojoType) {
        return RestAssured
            .given()
                .log().all()
                .spec(reqSpec)
                .queryParams(queryParams)
            .when()
                .get(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(200)
            .extract()
                .as(pojoType);
    }

    public Response post(RequestSpecification reqSpec, String endPoint, Object body) {
        return RestAssured
            .given()
                .log().all()
                .spec(reqSpec)
                .body(body)
            .when()
                .post(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(201)
            .extract()
                .response();
    }

    public <T> T post(RequestSpecification reqSpec, String endPoint, Object body, Class<T> pojoType) {
        return RestAssured
            .given()
                .log().all()
                .spec(reqSpec)
                .body(body)
            .when()
                .post(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(201)
            .extract()
                .as(pojoType);
    }
}
