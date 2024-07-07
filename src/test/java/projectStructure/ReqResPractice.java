package projectStructure;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projectStructure.pojo.Root;

class BaseTest {
    String baseURI = "https://reqres.in";
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeMethod
    public void beforeMethod() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
//              .setAuth(new BasicAuthScheme() {{
//                  setUserName("");
//                  setPassword("");
//              }})
//             .setProxy(
//                      new ProxySpecification("your-proxy-host", 9090, "http")
//                               .withAuth("proxy-username", "proxy-password")
//              )
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "*/*")
                .addPathParam("pathParam", "api")
            .build();

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody("data[0].email", Matchers.equalTo("michael.lawson@reqres.in"))
                .build();
    }

}

public class ReqResPractice extends BaseTest {

    @Test(testName = "LIST USERS", groups = {"ID-01"})
    public void test_1() {

        Root responsePojo = RestAssured
            .given().log().all()
                .spec(reqSpec)
                .queryParam("page", "2")
            .when()
                .get("/{pathParam}/users")
            .then()
                .spec(resSpec)
                .extract()
                .as(Root.class);

        System.out.println(responsePojo);

    }

    @Test(testName = "LIST USERS_2", groups = {"ID-02"})
    public void test_2() {

        Root responsePojo = RestAssured
            .given()
                .log().all()
                .spec(reqSpec)
                .queryParam("page", "1")
            .when()
                .get("/{pathParam}/users")
            .then()
                .spec(resSpec)
                .extract()
                .as(Root.class);

        System.out.println(responsePojo);

    }

}
