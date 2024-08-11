package practice.tutorials;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Intro_01_Get_and_Post_Request {

    static {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getRequest() {

        String getResponse = RestAssured
            .given()
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("page", "2")
            .when()
                .get("/api/users")
            .then()
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
            .assertThat()
                .statusCode(201)
                .body("name", Matchers.equalTo("Sachin Kn"))
                .body("job", Matchers.equalTo("Developer"))
            .extract()
                .asString();

        System.out.println("postResponse --> " + postResponse);
    }

    @Test
    public void dynamicPathParameterRequest() {

        String postResponse = RestAssured
            .given()
                .pathParam("path1", "api") // Set the path Params
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("page", "2")
                .body("")
            .when()
                .get("/{path1}/users")  // Get the path Params
            .then()
                .extract()
                .body()
                .asString();

        System.out.println("postResponse --> " + postResponse);
    }

    @Test(enabled = false) // This test is just for example
    public void allTheMethods() {

        SessionFilter session = new SessionFilter();

        RestAssured
            .given()
                .header("Content-Type", "application/json; charset=utf-8")
                .pathParam("path1", "api")
                .queryParam("page", "2")
                .formParam("", "")
                .body("{ \"name\": \"Sachin\" }")
                .filter(session)
                .log()
                .all()
            .when()
                .post("/api/users")
            .then()
                .log()
                .all()
                .assertThat()
                    .statusCode(200)
                    .body("page", Matchers.equalTo(2))
                .extract()
                    .body()
                    .asString();

    }
}
