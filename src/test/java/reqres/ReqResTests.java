package reqres;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import libs.ApiActions;
import libs.BaseTest;
import libs.utils.TestNGRetry;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.Root;
import pojo.User;

import java.util.HashMap;
import java.util.Map;

public class ReqResTests extends BaseTest {

    @Test(testName = "Create User-1", dataProvider = "genericDataProvider", groups = {"Id-04", "smoke"},  retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void createUserTest_1(ApiActions actions, JsonPath testData) throws Exception {
        Response response = actions.post(reqSpec, "/api/users", "{\"name\": \"Sachin\", \"job\": \"Tester\"}");
        System.out.println(response.asPrettyString());
    }

    @Test(testName = "Create User-2", dataProvider = "genericDataProvider", groups = {"Id-04", "smoke"},  retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void createUserTest_2(ApiActions actions, JsonPath testData) throws Exception {

        Map<String, Object> userCreateData = new HashMap<>();
        userCreateData.put("name", "Sachin");
        userCreateData.put("job", "Tester");

        Response response = actions.post(reqSpec, "/api/users", userCreateData);
        System.out.println(response.asPrettyString());
    }

    @Test(testName = "Create User-3", dataProvider = "genericDataProvider", groups = {"Id-04", "smoke"},  retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void createUserTest_3(ApiActions actions, JsonPath testData) throws Exception {

        User user = new User();
        user.setName("Sachin");
        user.setJob("Tester");

        Response response = actions.post(reqSpec, "/api/users", user);
        System.out.println(response.asPrettyString());
    }

    @Test(testName = "Create User-4", dataProvider = "genericDataProvider", groups = {"Id-04", "smoke"},  retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void createUserTest_4(ApiActions actions, JsonPath testData) throws Exception {

        User user = new User();
        user.setName("Sachin");
        user.setJob("Tester");

        User userResponse = actions.post(reqSpec, "/api/users", user, User.class);

        Assert.assertEquals(userResponse.getName(), "Sachin");
        Assert.assertEquals(userResponse.getJob(), "Tester");
        Assert.assertNotNull(userResponse.getId());
        Assert.assertNotNull(userResponse.getCreatedAt());
    }

    @Test(testName = "Get User-1", dataProvider = "genericDataProvider", groups = {"Id-05", "smoke"}, retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void getUserTest_1(ApiActions actions, JsonPath testData) {
        Response response = actions.get(reqSpec, "/api/users/2");

        JsonPath jsonPath = new JsonPath(response.asString());

        Assert.assertEquals(String.valueOf(jsonPath.getMap("data").get("email")), "janet.weaver@reqres.in");
        Assert.assertEquals(String.valueOf(jsonPath.getMap("data").get("first_name")), "Janet");
        Assert.assertEquals(String.valueOf(jsonPath.getMap("data").get("last_name")), "Weaver");
    }

    @Test(testName = "Get User-2", dataProvider = "genericDataProvider", groups = {"Id-05", "smoke"}, retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void getUserTest_2(ApiActions actions, JsonPath testData) {
        Response response = actions.get(reqSpec, "/api/users/2");

        Root root = response.as(Root.class);

        Assert.assertEquals(root.getData().getEmail(), "janet.weaver@reqres.in");
        Assert.assertEquals(root.getData().getFirst_name(), "Janet");
        Assert.assertEquals(root.getData().getLast_name(), "Weaver");
    }

    @Test(testName = "Get User-3", dataProvider = "genericDataProvider", groups = {"Id-05", "smoke"}, retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void getUserTest_3(ApiActions actions, JsonPath testData) {
        Root root = actions.get(reqSpec, "/api/users/2", Root.class);

        Assert.assertEquals(root.getData().getEmail(), "janet.weaver@reqres.in");
        Assert.assertEquals(root.getData().getFirst_name(), "Janet");
        Assert.assertEquals(root.getData().getLast_name(), "Weaver");
    }

    @Test(testName = "Update User", dataProvider = "genericDataProvider", groups = {"Id-06", "reg"}, retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "holdPayment"})
    public void updateUserTest(ApiActions actions, JsonPath testData) throws Exception {


    }

    @Test(testName = "Partial Update User", dataProvider = "genericDataProvider", groups = {"Id-06", "reg"}, retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "holdPayment"})
    public void partialUpdateUserTest(ApiActions actions, JsonPath testData) throws Exception {


    }
}















