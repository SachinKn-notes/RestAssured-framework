package reqres;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import libs.ApiActions;
import libs.BaseTest;
import libs.utils.TestNGRetry;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.User;

public class ReqResTests extends BaseTest {

    @Test(testName = "Create User", dataProvider = "genericDataProvider", groups = {"Id-04", "smoke"},  retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "fullPayment"})
    public void createUserTest(ApiActions actions, JsonPath testData) throws Exception {


    }

    @Test(testName = "Get User", dataProvider = "genericDataProvider", groups = {"Id-05", "smoke"}, retryAnalyzer = TestNGRetry.class)
    @Parameters(value = {"cruise", "depositPayment"})
    public void getUserTest(ApiActions actions, JsonPath testData) {
        Response response = actions.get(reqSpec, "/api/users/2");

        JsonPath jsonPath = new JsonPath(response.asString());

        Assert.assertEquals(String.valueOf(jsonPath.getMap("data").get("email")), "janet.weaver@reqres.in");
        Assert.assertEquals(String.valueOf(jsonPath.getMap("data").get("first_name")), "Janet");
        Assert.assertEquals(String.valueOf(jsonPath.getMap("data").get("last_name")), "Weaver");
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















