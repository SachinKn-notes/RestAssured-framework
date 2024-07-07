package practice;

import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ProxySpecification;

public class Sample {

    public static void main(String[] args) {

        new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setAuth(new BasicAuthScheme() {{ setUserName(""); setPassword(""); }})
                .setProxy(new ProxySpecification("123.123.1.1", 9090, "http").withAuth("", ""))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.ANY)


    }

}
