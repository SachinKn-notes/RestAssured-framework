package Tutorials;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Intro_01_Get_Post_JsonPath {

    static {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getRequest() {

        String getResponse = RestAssured
            .given()
//                .log()
//                .all()
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("page", "2")
            .when()
                .get("/api/users")
            .then()
//                .log()
//                .all()
            .assertThat()
                .statusCode(200)
                .body("page", Matchers.equalTo(2))
                .body("data[0].email", Matchers.equalTo("michael.lawson@reqres.in"))
            .extract()
                .body()
                .asString();

        System.out.println("getResponse --> " + getResponse);
    }

    @Test
    public void postRequest() {

        String postResponse = RestAssured
            .given()
//                .log()
//                .all()
                .queryParam("", "")
                .header("Content-Type", "application/json; charset=utf-8")
                .body("""
                        {
                            "name": "Sachin Kn",
                            "job": "Developer"
                        }
                """)
            .when()
                .post("/api/users")
            .then()
//                .log()
//                .all()
            .assertThat()
                .statusCode(201)
                .body("name", Matchers.equalTo("Sachin Kn"))
                .body("job", Matchers.equalTo("Developer"))
            .extract()
                .asString();

        System.out.println("postResponse --> " + postResponse);
    }

    @Test
    public void jsonPathTest() {

        String getResponse = RestAssured
            .given()
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("page", "2")
            .when()
                .get("/api/users")
            .then()
            .extract()
                .body()
                .asString();

        JsonPath jsonPath = new JsonPath(getResponse);

        System.out.println("jsonPath: first_name --> " + jsonPath.getString("data[0].first_name"));
        System.out.println("jsonPath: last_name --> " + jsonPath.getString("data[0].last_name"));
        System.out.println("jsonPath: email --> " + jsonPath.getString("data[0].email"));

    }
}
