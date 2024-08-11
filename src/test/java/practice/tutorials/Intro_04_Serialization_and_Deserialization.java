package practice.tutorials;

import PojoClasses_Practice.Course;
import PojoClasses_Practice.Dashboard;
import PojoClasses_Practice.Root;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Intro_04_Serialization_and_Deserialization {

    @Test // Converts a Java object, such as a Plain Old Java Object (POJO), into a format like JSON
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

    @Test // The opposite of serialization, deserialization converts the serialized data back into a Java object.
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
}
