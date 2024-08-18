package libs;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import libs.utils.ReporterUtils;
import pojo.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

        Response response = RestAssured
            .given()
                .log().all()
                .filter(new RequestLoggingFilter(new PrintStream(requestOutputStream)))
                .filter(new ResponseLoggingFilter(new PrintStream(responseOutputStream)))
                .spec(reqSpec)
            .when()
                .get(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(200)
            .extract()
                .response();

        ReporterUtils.writeReqAndResLogsToReport(Status.PASS, requestOutputStream.toString(), responseOutputStream.toString());

        return response;
    }

    public Response get(RequestSpecification reqSpec, String endPoint, Map<String, Object> queryParams) {
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

        Response response = RestAssured
            .given()
                .log().all()
                .filter(new RequestLoggingFilter(new PrintStream(requestOutputStream)))
                .filter(new ResponseLoggingFilter(new PrintStream(responseOutputStream)))
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

        ReporterUtils.writeReqAndResLogsToReport(Status.PASS, requestOutputStream.toString(), responseOutputStream.toString());

        return response;
    }

    public <T> T get(RequestSpecification reqSpec, String endPoint, Class<T> pojoType) {
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

        T responsePojo = RestAssured
            .given()
                .log().all()
                .filter(new RequestLoggingFilter(new PrintStream(requestOutputStream)))
                .filter(new ResponseLoggingFilter(new PrintStream(responseOutputStream)))
                .spec(reqSpec)
            .when()
                .get(endPoint)
            .then()
                .log().all()
            .assertThat()
                .statusCode(200)
            .extract()
                .as(pojoType);

        ReporterUtils.writeReqAndResLogsToReport(Status.PASS, requestOutputStream.toString(), responseOutputStream.toString());

        return responsePojo;
    }

    public <T> T get(RequestSpecification reqSpec, String endPoint, Map<String, Object> queryParams, Class<T> pojoType) {
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

        T responsePojo = RestAssured
            .given()
                .log().all()
                .filter(new RequestLoggingFilter(new PrintStream(requestOutputStream)))
                .filter(new ResponseLoggingFilter(new PrintStream(responseOutputStream)))
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

        ReporterUtils.writeReqAndResLogsToReport(Status.PASS, requestOutputStream.toString(), responseOutputStream.toString());

        return responsePojo;
    }

    public Response post(RequestSpecification reqSpec, String endPoint, Object body) {
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

        Response response = RestAssured
            .given()
                .log().all()
                .filter(new RequestLoggingFilter(new PrintStream(requestOutputStream)))
                .filter(new ResponseLoggingFilter(new PrintStream(responseOutputStream)))
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

        ReporterUtils.writeReqAndResLogsToReport(Status.PASS, requestOutputStream.toString(), responseOutputStream.toString());

        return response;
    }

    public <T> T post(RequestSpecification reqSpec, String endPoint, Object body, Class<T> pojoType) {
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

        T responsePojo = RestAssured
            .given()
                .log().all()
                .filter(new RequestLoggingFilter(new PrintStream(requestOutputStream)))
                .filter(new ResponseLoggingFilter(new PrintStream(responseOutputStream)))
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

        ReporterUtils.writeReqAndResLogsToReport(Status.PASS, requestOutputStream.toString(), responseOutputStream.toString());

        return responsePojo;
    }
}
