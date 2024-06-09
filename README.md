# RestAssured-tutorials

## 0. Some important codes.

**Convertions**
```
from JsonString to Pojo		-->  ResRoot person = new Gson().fromJson(myString, ResRoot.class);
from Pojo to JsonString		-->  String json = new Gson().toJson(myPojo);
from JsonString to JsonPath	-->  JsonPath jsonPath = new JsonPath(myStringValue);
from Response to Pojo		-->  myResRoot details = myResponse.as(myResRoot.class);
from Response to jsonString	-->  String resString = myResponse.asString();
```

**To print response**
```
to log all response      	-->  myResponse.then().log().all();
to print only json	 	-->  myResponse.print();
to prettyPrint only json 	-->  myResponse.prettyPrint();
```

**Assertions**
```
myResponse.then().assertThat().
	   statusCode(200).
	   body("isSucceed", equalTo(true)).
	   body("data.list[0].startDateTime", equalTo("01-Aug-2023")).
	   header("Content-Type", "application/json; charset=utf-8");
```

**To get the count of JsonList**
```
int count = jsonPath.getInt("data.list.size()");
```

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
```
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
```

## 4. Dynamic Path Parameter Request.
```
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
```

## 5. Session Filter Example.
```
public void sessionFilterExample() {

        SessionFilter session = new SessionFilter();


        RestAssured
            .given()
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("page", "2")
                .body("""
                        {
                            "username": "SachinKn",
                            "password": "Sachin@123"
                        }
                        """)
                .filter(session)  // This will store the session cookie after login
            .when()
                .post("/api/users")
            .then()
                .log()
                .status();


        RestAssured
            .given()
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("page", "2")
                .filter(session)  // Here it will use the same session and treated as login user.
            .when()
                .get("/api/users")
            .then()
                .log()
                .status();
}
```

## 6. Json Path Exercise.
```
public void jsonPathTest() {

        JsonPath jsonPath = new JsonPath("""
            {
                "dashboard": {
                    "purchaseAmount": 910,
                    "website": "sachinkn.in"
                },
                "courses": [
                    {
                        "title": "Selenium Java",
                        "price": 50,
                        "copies": 6
                    },
                    {
                        "title": "Cypress",
                        "price": 40,
                        "copies": 4
                    },
                    {
                        "title": "Playwright",
                        "price": 45,
                        "copies": 10
                    }
                ]
            }
        """);
        System.out.println("1. Print No of courses returned by API\n>> " + jsonPath.getInt("courses.size()"));
        System.out.println("\n2. Print Purchase Amount\n>> " + jsonPath.getInt("dashboard.purchaseAmount"));
        System.out.println("\n3. Print Title of the first course\n>> " + jsonPath.getString("courses[0].title"));
        System.out.println("\n4. Print All course titles and their respective Prices\n>> " + jsonPath.getString("courses[0].title"));

        System.out.println("\n5. Print no of copies sold by Playwright Course");
        for (int i=0; i<jsonPath.getInt("courses.size()"); i++) {
            if (jsonPath.getString("courses[" + i + "].title").equals("Playwright")) {
                System.out.println(">> " + jsonPath.getInt("courses[" + i + "].price"));
                break;
            }
        }

        System.out.println("\n6. Verify if Sum of all Course prices matches with Purchase Amount");
        int totalSum = 0;
        for (int i=0; i<jsonPath.getInt("courses.size()"); i++) {
            totalSum += jsonPath.getInt("courses[" + i + "].price") * jsonPath.getInt("courses[" + i + "].copies");
        }
        System.out.println(">> purchaseAmount -> " + jsonPath.getInt("dashboard.purchaseAmount"));
        System.out.println(">> totalSum -> " + totalSum);

}
```
**Output**
```
1. Print No of courses returned by API
>> 3

2. Print Purchase Amount
>> 910

3. Print Title of the first course
>> Selenium Java

4. Print All course titles and their respective Prices
>> Selenium Java

5. Print no of copies sold by Playwright Course
>> 45

6. Verify if Sum of all Course prices matches with Purchase Amount
>> purchaseAmount -> 910
>> totalSum -> 910
```

## 7. Serialization - Converts a Java object, such as a Plain Old Java Object (POJO), into a format like JSON
```
public void jsonSerialization() {
	Dashboard dashboard = new Dashboard();
	dashboard.setWebsite("sachinkn.in");
	dashboard.setPurchaseAmount(910);
	
	Course course1 = new Course();
	course1.setTitle("Selenium Java");
	course1.setPrice(50);
	course1.setCopies(6);
	
	Course course2 = new Course();
	course2.setTitle("Cypress");
	course2.setPrice(40);
	course2.setCopies(4);
	
	Course course3 = new Course();
	course3.setTitle("Playwright");
	course3.setPrice(45);
	course3.setCopies(10);
	
	ArrayList<Course> courses = new ArrayList<>();
	courses.add(course1);
	courses.add(course2);
	courses.add(course3);
	
	Root rootPojo = new Root();
	rootPojo.setDashboard(dashboard);
	rootPojo.setCourses(courses);
	
	String jsonString  = new Gson().toJson(rootPojo);
	System.out.println(jsonString);
}
```

## 7. Deserialization - he opposite of serialization, deserialization converts the serialized data back into a Java object.
```
public void jsonDeserialization() {

    String jsonString = """
        {
            "dashboard": {
                "purchaseAmount": 910,
                "website": "sachinkn.in"
            },
            "courses": [
                {
                    "title": "Selenium Java",
                    "price": 50,
                    "copies": 6
                },
                {
                    "title": "Cypress",
                    "price": 40,
                    "copies": 4
                },
                {
                    "title": "Playwright",
                    "price": 45,
                    "copies": 10
                }
            ]
        }
    """;

    Root rootPojo = new Gson().fromJson(jsonString, Root.class);
    System.out.println(rootPojo);
}
```




