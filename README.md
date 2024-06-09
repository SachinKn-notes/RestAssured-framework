# RestAssured-tutorials

## 1. All the methods of Rest Assured.

```
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
```

## 2. Simple GET Request.
```
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
```

## 3. Simple POST Request.
