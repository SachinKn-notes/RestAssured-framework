package tutorials;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Intro_05_SpecBuilder {

    String baseURI = "https://reqres.in";
    @Test
    public void RequestSpecBuilder() {
        SessionFilter session = new SessionFilter();

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
//              .setAuth(new BasicAuthScheme() {{
//                  setUserName("your-username");
//                  setPassword("your-password");
//              }})
//             .setProxy(
//                      new ProxySpecification("your-proxy-host", 9090, "http")
//                               .withAuth("proxy-username", "proxy-password")
//              )
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addPathParam("pathParam", "api")
                .addQueryParam("page", "2")
//              .addFormParam("", "")
            .build();

        RestAssured
            .given()
                .spec(reqSpec).body("{ \"name\": \"Sachin\" }")
                .headers("", "") // After build also we can add other request specs
                .filter(session)
                .log()
                .all()
            .when()
                .post("/{pathParam}/users")
            .then()
                .log().all();
    }

    @Test
    public void ResponseSpecBuilder() {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
//              .setAuth(new BasicAuthScheme() {{
//                  setUserName("your-username");
//                  setPassword("your-password");
//              }})
//              .setProxy(
//                      new ProxySpecification("your-proxy-host", 9090, "http")
//                              .withAuth("proxy-username", "proxy-password")
//              )
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addPathParam("pathParam", "api")
                .addQueryParam("page", "2")
//              .addFormParam("", "")
            .build();

        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody("data[0].email", Matchers.equalTo("michael.lawson@reqres.in"))
                .build();

        RestAssured
            .given()
                .spec(reqSpec) // Request Spec Builder
            .when()
                .get("/{pathParam}/users")
            .then()
                .spec(resSpec) // Response Spec Builder
                .log().all();

    }
}
