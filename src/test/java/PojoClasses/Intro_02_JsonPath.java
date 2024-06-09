package PojoClasses;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class Intro_02_JsonPath {

    @Test
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
}
