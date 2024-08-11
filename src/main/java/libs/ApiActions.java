package libs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
}


