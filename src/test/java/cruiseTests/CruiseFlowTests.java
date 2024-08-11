package cruiseTests;


import io.restassured.path.json.JsonPath;
import libs.ApiActions;
import libs.BaseTest;
import libs.utils.TestNGRetry;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CruiseFlowTests extends BaseTest {
    @Test(testName = "Cruise Flow Full Payment Test", dataProvider = "genericDataProvider", groups = {"Id-01", "smoke"})
    @Parameters(value = {"cruise", "fullPayment"})
    public void cruiseFlowFullPaymentTest(ApiActions actions, JsonPath testData) throws Exception {

    }

    @Test(testName = "Cruise Flow Deposit Payment Test", dataProvider = "genericDataProvider", groups = {"Id-02", "smoke"})
    @Parameters(value = {"cruise", "depositPayment"})
    public void cruiseFlowDepositPaymentTest(ApiActions actions, JsonPath testData) {

    }

    @Test(testName = "Cruise Flow Hold Payment Test", dataProvider = "genericDataProvider", groups = {"Id-03", "reg"}, retryAnalyzer = TestNGRetry.class, timeOut = 500)
    @Parameters(value = {"cruise", "holdPayment"})
    public void cruiseFlowHoldPaymentTest(ApiActions actions, JsonPath testData) throws Exception {


    }
}















